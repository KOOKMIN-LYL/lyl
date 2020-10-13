package com.kookmin.lyl.module.product.service;

import com.kookmin.lyl.module.category.domain.Category;
import com.kookmin.lyl.module.category.repository.CategoryRepository;
import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.dto.ProductCreateInfo;
import com.kookmin.lyl.module.product.dto.ProductDetails;
import com.kookmin.lyl.module.product.repository.ProductRepository;
import com.kookmin.lyl.module.shop.domain.Shop;
import com.kookmin.lyl.module.shop.repository.ShopRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceImplTest {
    @Autowired private ProductService productService;
    @Autowired private ShopRepository shopRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private ProductRepository productRepository;
    private Long shopNumber;
    private Long categoryId;

    @BeforeEach
    public void setUp() {
        Shop shop = new Shop();
        shop = shopRepository.save(shop);

        Category category = Category.builder()
                .name("category1")
                .build();
        category = categoryRepository.save(category);

        Product product1 = Product.builder()
                .shop(shop)
                .category(category)
                .manufacturer("제조사")
                .origin("원산지")
                .price(1000)
                .name("상품1")
                .build();

        Product product2 = Product.builder()
                .shop(shop)
                .category(category)
                .manufacturer("제조사")
                .origin("원산지")
                .price(2000)
                .name("상품2")
                .build();

        Product product3 = Product.builder()
                .shop(shop)
                .category(category)
                .manufacturer("제조사")
                .origin("원산지")
                .price(3000)
                .name("상품3")
                .build();

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        this.categoryId = category.getId();
        this.shopNumber = shop.getShopNumber();
    }

    @Test
    @DisplayName("createProduct_성공_테스트")
    public void test_createProduct_success() {
        ProductCreateInfo productCreateInfo = new ProductCreateInfo();
        productCreateInfo.setCategoryId(categoryId);
        productCreateInfo.setShopId(shopNumber);
        productCreateInfo.setManufacturer("제조사");
        productCreateInfo.setName("추가물품");
        productCreateInfo.setOrigin("원산지");
        productCreateInfo.setPrice(10000);

        Long productId = productService.createProduct(productCreateInfo);

        ProductDetails result = productService.findProduct(productId);

        assertThat(result)
                .hasFieldOrPropertyWithValue("productNumber", productId)
                .hasFieldOrPropertyWithValue("Manufacturer", productCreateInfo.getManufacturer())
                .hasFieldOrPropertyWithValue("name", productCreateInfo.getName())
                .hasFieldOrPropertyWithValue("origin", productCreateInfo.getOrigin())
                .hasFieldOrPropertyWithValue("price", productCreateInfo.getPrice())
                .hasFieldOrPropertyWithValue("categoryId", productCreateInfo.getCategoryId())
                .hasFieldOrPropertyWithValue("shopNumber", productCreateInfo.getShopId());
    }
}