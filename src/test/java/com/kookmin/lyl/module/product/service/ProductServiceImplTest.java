package com.kookmin.lyl.module.product.service;

import com.kookmin.lyl.module.category.domain.Category;
import com.kookmin.lyl.module.category.repository.CategoryRepository;
import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.domain.ProductOptionType;
import com.kookmin.lyl.module.product.dto.*;
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
    private Long firstProduct;

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
        this.firstProduct = product1.getId();
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

    @Test
    @DisplayName("editProduct_성공_테스트")
    public void test_editProduct_success() {
        ProductEditInfo productEditInfo = new ProductEditInfo();
        productEditInfo.setManufacturer("제조시 수정");
        productEditInfo.setName("품목 수정");
        productEditInfo.setOrigin("원산지 수정");
        productEditInfo.setPrice(999);

        productService.editProduct(firstProduct, productEditInfo);

        ProductDetails result = productService.findProduct(firstProduct);

        assertThat(result)
                .hasFieldOrPropertyWithValue("Manufacturer", productEditInfo.getManufacturer())
                .hasFieldOrPropertyWithValue("name", productEditInfo.getName())
                .hasFieldOrPropertyWithValue("origin", productEditInfo.getOrigin())
                .hasFieldOrPropertyWithValue("price", productEditInfo.getPrice());
    }

    @Test
    @DisplayName("deleteProduct_성공_테스트")
    public void test_deleteProduct_success() {
        int size = productRepository.findAll().size();
        productService.deleteProduct(firstProduct);

        assertThat(productRepository.findAll().size())
                .isEqualTo(size-1);
    }

    @Test
    @DisplayName("findProduct_성공_테스트")
    public void test_findProduct_success() {
        ProductDetails result = productService.findProduct(firstProduct);
        System.out.println(result);
    }

    @Test
    @DisplayName("addProductOption_성공_테스트")
    public void test_addProductOption_success() {
        ProductOptionCreateInfo productOptionCreateInfo = new ProductOptionCreateInfo();
        productOptionCreateInfo.setType(ProductOptionType.SIZE);
        productOptionCreateInfo.setOption("옵션1");
        productOptionCreateInfo.setProductNumber(firstProduct);

        Long productOptionId = productService.addProductOption(productOptionCreateInfo);
        ProductOptionDetails result = productService.findProductOption(productOptionId);

        assertThat(result)
                .hasFieldOrPropertyWithValue("type", productOptionCreateInfo.getType().toString())
                .hasFieldOrPropertyWithValue("id", productOptionId)
                .hasFieldOrPropertyWithValue("option", productOptionCreateInfo.getOption())
                .hasFieldOrPropertyWithValue("productNumber", productOptionCreateInfo.getProductNumber());


    }
}