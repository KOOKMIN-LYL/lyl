package com.kookmin.lyl.module.product.service;

import com.kookmin.lyl.infra.util.SearchCondition;
import com.kookmin.lyl.module.category.domain.Category;
import com.kookmin.lyl.module.category.repository.CategoryRepository;
import com.kookmin.lyl.module.product.domain.Product;
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
                .orElseThrow();
    }

    @Override
    public void stopSellingProduct(Long productNumber) {

    }

    @Override
    public void sellingProduct(Long productNumber) {

    }

    @Override
    public void deleteProduct(Long productNumber) {

    }

    @Override
    public ProductDetails findProduct(Long productNumber) {
        return null;
    }

    @Override
    public List<ProductDetails> searchProducts(Pageable pageable, SearchCondition searchCondition) {
        return null;
    }

    @Override
    public void addProductOption(Long productNumber, ProductOptionInfo productOptionInfo) {

    }

    @Override
    public void editProductOption(Long productNumber, Long productOptionId, ProductOptionInfo productOptionInfo) {

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
