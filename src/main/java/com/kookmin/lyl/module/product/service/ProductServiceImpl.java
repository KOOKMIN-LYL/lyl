package com.kookmin.lyl.module.product.service;

import com.kookmin.lyl.infra.util.SearchCondition;
import com.kookmin.lyl.module.category.domain.Category;
import com.kookmin.lyl.module.category.repository.CategoryRepository;
import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.domain.ProductOption;
import com.kookmin.lyl.module.product.dto.*;
import com.kookmin.lyl.module.product.repository.ProductOptionRepository;
import com.kookmin.lyl.module.product.repository.ProductRepository;
import com.kookmin.lyl.module.shop.domain.Shop;
import com.kookmin.lyl.module.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;

    @Override
    public Long createProduct(ProductCreateInfo productCreateInfo) {
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

        return product.getProductNumber();
    }

    @Override
    public void editProduct(Long productNumber, ProductEditInfo productEditInfo) {
        Product product = productRepository.findById(productNumber)
                .orElseThrow(EntityNotFoundException::new);

        product.eidtProduct(productEditInfo.getName(),
                productEditInfo.getPrice(),
                productEditInfo.getOrigin(),
                productEditInfo.getManufacturer());
    }

    @Override
    public void stopSellingProduct(Long productNumber) {
        Product product = productRepository.findById(productNumber)
                .orElseThrow(EntityNotFoundException::new);

        product.soldOutProduct();
    }

    @Override
    public void sellingProduct(Long productNumber) {
        Product product = productRepository.findById(productNumber)
                .orElseThrow(EntityNotFoundException::new);

        product.onSaleProduct();
    }

    @Override
    public void deleteProduct(Long productNumber) {
        productRepository.deleteById(productNumber);
    }

    @Override
    public ProductDetails findProduct(Long productNumber) {
        Product product = productRepository.findById(productNumber).orElseThrow(EntityNotFoundException::new);
        return new ProductDetails(product);
    }

    @Override
    public List<ProductDetails> searchProducts(Pageable pageable, SearchCondition searchCondition) {
        return null;
    }

    @Override
    public Long addProductOption(ProductOptionCreateInfo productOptionInfo) {
        Product product = productRepository.findById(productOptionInfo.getProductNumber())
                .orElseThrow(EntityNotFoundException::new);

        ProductOption productOption = ProductOption.builder()
                .option(productOptionInfo.getOption())
                .type(productOptionInfo.getType())
                .product(product)
                .build();

        productOption = productOptionRepository.save(productOption);

        return productOption.getId();
    }

    @Override
    public void editProductOption(ProductOptionEditInfo productOptionEditInfo) {
        Product product = productRepository.findById(productOptionEditInfo.getProductNumber())
                .orElseThrow(EntityNotFoundException::new);

        //TODO:: product와 연관관계가 없는 productOption이 수정될 가능성이 있으므로 이를 방지해야함, 여러 로직에서 쓰일 수 있으므로 메소드로 분리
        List<ProductOption> productOptions =product.getProductOptions();

        ProductOption productOption = productOptionRepository.findById(productOptionEditInfo.getId())
                .orElseThrow(EntityNotFoundException::new);

        if(!productOptions.contains(productOption)) {
            throw new RuntimeException();
        }

        productOption.editProductOption(
                productOptionEditInfo.getOption(),
                productOptionEditInfo.getType()
        );
    }

    @Override
    public void deleteProductOption(Long productNumber, Long productOptionId) {
        
    }

    @Override
    public ProductOptionDetails findProductOption(Long productOptionId) {
        return null;
    }

    @Override
    public List<ProductOptionDetails> findProductOptions(Long ProductNumber) {
        return null;
    }
}
