package com.kookmin.lyl.module.product.service;

import com.kookmin.lyl.module.product.dto.ProductDetails;

import java.util.List;

public interface ProductServiceWithCache extends ProductService{
    public ProductDetails findProductWithCache(Long productNumber, String memberId);
    public List<ProductDetails> findRecentSearchedProducts(String memberId);
    public List<ProductDetails> findTop10Products();
}
