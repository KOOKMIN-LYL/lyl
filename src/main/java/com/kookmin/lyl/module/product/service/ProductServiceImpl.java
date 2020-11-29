package com.kookmin.lyl.module.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kookmin.lyl.infra.util.SearchCondition;
import com.kookmin.lyl.module.category.domain.Category;
import com.kookmin.lyl.module.category.repository.CategoryRepository;
import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.domain.ProductOption;
import com.kookmin.lyl.module.product.domain.ProductOptionType;
import com.kookmin.lyl.module.product.dto.*;
import com.kookmin.lyl.module.product.repository.ProductCacheRepository;
import com.kookmin.lyl.module.product.repository.ProductOptionRepository;
import com.kookmin.lyl.module.product.repository.ProductRepository;
import com.kookmin.lyl.module.product.repository.ProductRepositorySearch;
import com.kookmin.lyl.module.shop.domain.Shop;
import com.kookmin.lyl.module.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("productService")
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductServiceWithCache{
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;
    private final ProductRepositorySearch productRepositorySearch;
    private final ProductCacheRepository productCacheRepository;

    @Override
    public Long createProduct(@NonNull ProductCreateInfo productCreateInfo) {
        Shop shop = shopRepository.findById(productCreateInfo.getShopId())
                .orElseThrow(EntityNotFoundException::new);

        Category category = categoryRepository.findById(productCreateInfo.getCategoryId())
                .orElseThrow(EntityNotFoundException::new);

        Product product = Product.builder()
                .name(productCreateInfo.getName())
                .price(productCreateInfo.getPrice())
                .origin(productCreateInfo.getOrigin())
                .manufacturer(productCreateInfo.getManufacturer())
                .category(category)
                .shop(shop)
                .build();

        product = productRepository.save(product);

        return product.getId();
    }

    @Override
    public void editProduct(@NonNull Long productNumber, @NonNull ProductEditInfo productEditInfo) {
        Product product = productRepository.findById(productNumber)
                .orElseThrow(EntityNotFoundException::new);

        product.eidtProduct(productEditInfo.getName(),
                productEditInfo.getPrice(),
                productEditInfo.getOrigin(),
                productEditInfo.getManufacturer());
    }

    @Override
    public void stopSellingProduct(@NonNull Long productNumber) {
        Product product = productRepository.findById(productNumber)
                .orElseThrow(EntityNotFoundException::new);

        product.soldOutProduct();
    }

    @Override
    public void sellingProduct(@NonNull Long productNumber) {
        Product product = productRepository.findById(productNumber)
                .orElseThrow(EntityNotFoundException::new);

        product.onSaleProduct();
    }

    @Override
    public void deleteProduct(@NonNull Long productNumber) {
        productRepository.deleteById(productNumber);
    }

    @Override
    public ProductDetails findProduct(@NonNull Long productNumber) {
        Product product = productRepository.findById(productNumber).orElseThrow(EntityNotFoundException::new);
        List<ProductOption> productOptions = productOptionRepository.findByProductId(product.getId());

        ProductDetails productDetails = new ProductDetails(product);
        List<ProductOptionDetails> productOptionDetails = new ArrayList<ProductOptionDetails>();

        for(ProductOption productOption : productOptions) {
            productOptionDetails.add(new ProductOptionDetails(productOption));
        }

        productDetails.setProductOptionDetails(productOptionDetails);

        return productDetails;
    }

    @Override
    public Page<ProductDetails> searchProducts(@NonNull Pageable pageable, @NonNull ProductSearchCondition searchCondition) {
        Page<ProductDetails> productDetails = productRepositorySearch.searchProductDetails(pageable, searchCondition);

        for(ProductDetails product : productDetails.getContent()) {
            List<ProductOption> productOptions = productOptionRepository.findByProductId(product.getProductNumber());
            List<ProductOptionDetails> productOptionDetails = new ArrayList<>();

            for(ProductOption productOption : productOptions) {
                productOptionDetails.add(new ProductOptionDetails(productOption));
            }

            product.setProductOptionDetails(productOptionDetails);
        }

        return productDetails;
    }

    @Override
    public Long addProductOption(@NonNull ProductOptionCreateInfo productOptionInfo) {
        Product product = productRepository.findById(productOptionInfo.getProductNumber())
                .orElseThrow(EntityNotFoundException::new);

        ProductOption productOption = ProductOption.builder()
                .option(productOptionInfo.getOption())
                .type(ProductOptionType.valueOf(productOptionInfo.getType()))
                .product(product)
                .build();

        productOption = productOptionRepository.save(productOption);

        return productOption.getId();
    }

    @Override
    public void editProductOption(@NonNull ProductOptionEditInfo productOptionEditInfo) {
        ProductOption productOption = this.validateProductOption(productOptionEditInfo.getProductNumber(),
                productOptionEditInfo.getId());

        productOption.editProductOption(
                productOptionEditInfo.getOption(),
                ProductOptionType.valueOf(productOptionEditInfo.getType())
        );
    }

    @Override
    public void deleteProductOption(@NonNull Long productNumber, @NonNull Long productOptionId) {
        this.validateProductOption(productNumber, productOptionId);
        productOptionRepository.deleteById(productOptionId);
    }

    @Override
    public ProductOptionDetails findProductOption(@NonNull Long productOptionId) {
        ProductOption productOption = productOptionRepository.findById(productOptionId)
                .orElseThrow(EntityNotFoundException::new);
        return new ProductOptionDetails(productOption);
    }

    @Override
    public List<ProductOptionDetails> findProductOptions(@NonNull Long productNumber) {
        List<ProductOption> productOptions = productOptionRepository.findByProductId(productNumber);
        List<ProductOptionDetails> productOptionDetails = new ArrayList<>();

        for(ProductOption productOption : productOptions) {
            productOptionDetails.add(new ProductOptionDetails(productOption));
        }

        return productOptionDetails;
    }

    //TODO:: product와 연관관계가 없는 productOption이 수정될 가능성이 있으므로 이를 방지해야함, 여러 로직에서 쓰일 수 있으므로 메소드로 분리
    private ProductOption validateProductOption(Long productNumber, Long productOptionId) {
        Product product = productRepository.findById(productNumber)
                .orElseThrow(EntityNotFoundException::new);

        List<ProductOption> productOptions =product.getProductOptions();

        ProductOption productOption = productOptionRepository.findById(productOptionId)
                .orElseThrow(EntityNotFoundException::new);

        if(!productOptions.contains(productOption)) {
            throw new RuntimeException();
        }

        return productOption;
    }

    @Override
    public ProductDetails findProductWithCache(@NonNull Long productNumber,
                                               @NonNull String memberId) {

        ProductDetails productDetails = this.findProduct(productNumber);

        productCacheRepository.add(memberId, productDetails);

        return productDetails;
    }

    @Override
    public List<ProductDetails> findRecentSearchedProducts(String memberId) {
        return productCacheRepository.findRecentSearchedProducts(memberId);
    }
}
