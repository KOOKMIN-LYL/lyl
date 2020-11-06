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
}
