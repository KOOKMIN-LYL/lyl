package com.kookmin.lyl.module.product.repository;

import com.kookmin.lyl.module.product.dto.ProductDetails;
import com.kookmin.lyl.module.product.dto.ProductSearchCondition;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface ProductRepositorySearch {
    Page<ProductDetails> searchProductDetails(Pageable pageable, ProductSearchCondition condition);
}
