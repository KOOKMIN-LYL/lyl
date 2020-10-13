package com.kookmin.lyl.module.product.service;

import com.kookmin.lyl.module.category.domain.Category;
import com.kookmin.lyl.module.category.repository.CategoryRepository;
import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.domain.ProductOptionType;
import com.kookmin.lyl.module.product.dto.*;
import com.kookmin.lyl.module.product.repository.ProductRepository;
import com.kookmin.lyl.module.shop.domain.Shop;
import com.kookmin.lyl.module.shop.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceImplTest {
    @Autowired private ProductService productService;
    @Autowired private ShopRepository shopRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private ProductRepository productRepository;
    @PersistenceContext
    EntityManager entityManager;

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
        productOptionCreateInfo.setType(ProductOptionType.SIZE.toString());
        productOptionCreateInfo.setOption("옵션1");
        productOptionCreateInfo.setProductNumber(firstProduct);

        Long productOptionId = productService.addProductOption(productOptionCreateInfo);
        ProductOptionDetails result = productService.findProductOption(productOptionId);

        assertThat(result)
                .hasFieldOrPropertyWithValue("type", productOptionCreateInfo.getType())
                .hasFieldOrPropertyWithValue("id", productOptionId)
                .hasFieldOrPropertyWithValue("option", productOptionCreateInfo.getOption())
                .hasFieldOrPropertyWithValue("productNumber", productOptionCreateInfo.getProductNumber());
    }

    @Test
    @DisplayName("editProductOption_성공_테스트")
    public void test_editProductOption_success() {
        ProductOptionCreateInfo productOptionCreateInfo = new ProductOptionCreateInfo();
        productOptionCreateInfo.setType(ProductOptionType.SIZE.toString());
        productOptionCreateInfo.setOption("옵션1");
        productOptionCreateInfo.setProductNumber(firstProduct);

        Long productOptionId = productService.addProductOption(productOptionCreateInfo);

        ProductOptionEditInfo productOptionEditInfo = new ProductOptionEditInfo();
        productOptionEditInfo.setId(productOptionId);
        productOptionEditInfo.setOption("옵션1 수정");
        productOptionEditInfo.setType(ProductOptionType.ETC.toString());
        productOptionEditInfo.setProductNumber(firstProduct);

        entityManager.flush(); entityManager.clear();

        productService.editProductOption(productOptionEditInfo);

        ProductOptionDetails result = productService.findProductOption(productOptionId);

        assertThat(result)
                .hasFieldOrPropertyWithValue("type", productOptionEditInfo.getType())
                .hasFieldOrPropertyWithValue("id", productOptionEditInfo.getId())
                .hasFieldOrPropertyWithValue("option", productOptionEditInfo.getOption())
                .hasFieldOrPropertyWithValue("productNumber", productOptionEditInfo.getProductNumber());
    }

    @Test
    @DisplayName("deleteProductOption_성공_테스트")
    public void test_deleteProductOption_success() {
        ProductOptionCreateInfo productOptionCreateInfo = new ProductOptionCreateInfo();
        productOptionCreateInfo.setType(ProductOptionType.SIZE.toString());
        productOptionCreateInfo.setOption("옵션1");
        productOptionCreateInfo.setProductNumber(firstProduct);

        Long productOptionId = productService.addProductOption(productOptionCreateInfo);

        entityManager.flush(); entityManager.clear();

        List<ProductOptionDetails> result = productService.findProductOptions(firstProduct);

        productService.deleteProductOption(firstProduct, productOptionId);

        assertThat(productService.findProductOptions(firstProduct).size())
                .isEqualTo(result.size() -1);
    }

    @Test
    @DisplayName("searchProduct_성공_테스트")
    public void test_searchProduct_success() {
        ProductSearchCondition condition = new ProductSearchCondition();
        condition.setCategoryId(categoryId);

        PageRequest request = PageRequest.of(0, 2);

        Page<ProductDetails> result = productService.searchProducts(request, condition);
        for(ProductDetails productDetails : result.getContent()){
            System.out.println(productDetails);
        }
    }
}