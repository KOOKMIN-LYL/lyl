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
import com.kookmin.lyl.module.product.service.ProductServiceWithCache;
import com.kookmin.lyl.module.shop.domain.Shop;
import com.kookmin.lyl.module.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceWithCache productService;

    @GetMapping(value = "/category/{categoryId}")
    public Page<ProductDetails> getCategoryProducts(Pageable pageable, ProductSearchCondition searchCondition) {
        Page<ProductDetails> result = productService.searchProducts(pageable, searchCondition);

        return result;
    }

    @GetMapping(value = "/product/{productId}")
    public ProductDetails getProductDetails(@PathVariable("productId") Long productId,
                                            @Nullable Principal principal) {
        if(principal == null) {
            return productService.findProduct(productId);
        }

        return productService.findProductWithCache(productId, principal.getName());
    }
}
