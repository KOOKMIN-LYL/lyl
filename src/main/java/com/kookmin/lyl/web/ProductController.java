package com.kookmin.lyl.web;

import com.kookmin.lyl.module.category.domain.Category;
import com.kookmin.lyl.module.category.repository.CategoryRepository;
import com.kookmin.lyl.module.order.dto.OrderProductInfo;
import com.kookmin.lyl.module.order.service.OrderService;
import com.kookmin.lyl.module.product.domain.ProductOptionType;
import com.kookmin.lyl.module.product.dto.ProductCreateInfo;
import com.kookmin.lyl.module.product.dto.ProductDetails;
import com.kookmin.lyl.module.product.dto.ProductOptionCreateInfo;
import com.kookmin.lyl.module.product.dto.ProductSearchCondition;
import com.kookmin.lyl.module.product.service.ProductService;
import com.kookmin.lyl.module.shop.domain.Shop;
import com.kookmin.lyl.module.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ShopRepository shopRepository;
    private final CategoryRepository categoryRepository;
    private final OrderService orderService;

    @GetMapping(value = "/category/{categoryId}")
    public Page<ProductDetails> getCategoryProducts(Pageable pageable, ProductSearchCondition searchCondition) {
        Page<ProductDetails> result = productService.searchProducts(pageable, searchCondition);

        return result;
    }

    @GetMapping(value = "/product/{productId}")
    public ProductDetails getProductDetails(@PathVariable("productId") Long productId) {
        return productService.findProduct(productId);
    }

    @PostConstruct
    public void setUpProduct() {
        Shop shop = new Shop();
        shop = shopRepository.save(shop);

        Category category = Category.builder()
                .name("category1")
                .build();
        category = categoryRepository.save(category);

        System.out.println("ID: " + category.getId());

        for(int i = 1; i <= 100; ++i) {
            ProductCreateInfo productCreateInfo = new ProductCreateInfo();
            productCreateInfo.setCategoryId(category.getId());
            productCreateInfo.setShopId(shop.getShopNumber());
            productCreateInfo.setManufacturer("제조사"+i);
            productCreateInfo.setName("추가물품"+i);
            productCreateInfo.setOrigin("원산지"+i);
            productCreateInfo.setPrice(i*100);

            ProductOptionCreateInfo productOptionCreateInfo = new ProductOptionCreateInfo();

            Long id = productService.createProduct(productCreateInfo);
            productOptionCreateInfo.setProductNumber(id);
            productOptionCreateInfo.setOption("옵션" + i);
            productOptionCreateInfo.setType(ProductOptionType.SIZE.toString());
            productService.addProductOption(productOptionCreateInfo);
        }

        OrderProductInfo orderProductInfo = new OrderProductInfo();
        orderProductInfo.setProductId(4L);
        orderProductInfo.setProductOptionId(5L);
        orderProductInfo.setQuantity(5);
        orderProductInfo.setDeliveryAddress("address");
        orderProductInfo.setRequest("request");

        System.out.println("ORDER : " + orderService.addCart("user", orderProductInfo));

        orderProductInfo.setQuantity(4);

        orderService.addCart("user", orderProductInfo);
    }
}
