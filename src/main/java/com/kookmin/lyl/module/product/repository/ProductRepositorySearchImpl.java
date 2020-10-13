package com.kookmin.lyl.module.product.repository;

import com.kookmin.lyl.infra.support.LylQuerydslRepositorySupport;
import com.kookmin.lyl.module.category.domain.QCategory;
import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.domain.QProduct;
import com.kookmin.lyl.module.product.dto.ProductDetails;
import com.kookmin.lyl.module.product.dto.ProductSearchCondition;
import com.kookmin.lyl.module.shop.domain.QShop;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.kookmin.lyl.module.category.domain.QCategory.*;
import static com.kookmin.lyl.module.product.domain.QProduct.product;
import static com.kookmin.lyl.module.shop.domain.QShop.*;

@Repository
public class ProductRepositorySearchImpl extends LylQuerydslRepositorySupport implements ProductRepositorySearch {

    public ProductRepositorySearchImpl() {
        super(Product.class);
    }

    @Override
    public Page<ProductDetails> searchProductDetails(Pageable pageable, ProductSearchCondition condition) {
        return applyPagination(pageable, contentQuery -> contentQuery
        .selectFrom(product)
        .leftJoin(product.category, category)
        .leftJoin(product.shop, shop)
        .where(categoryIdEq(condition.getCategoryId())));
    }

    private BooleanExpression categoryIdEq(Long categoryId) {
        return categoryId == null ? null : product.category.id.eq(categoryId);
    }
}
