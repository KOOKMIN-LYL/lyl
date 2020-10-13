package com.kookmin.lyl.web;

import com.kookmin.lyl.module.product.dto.ProductDetails;
import com.kookmin.lyl.module.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/category/{categoryId}")
    public Page<ProductDetails> getCategoryProducts(@PathVariable("categoryId") Long categoryId) {
        return null;
    }
}
