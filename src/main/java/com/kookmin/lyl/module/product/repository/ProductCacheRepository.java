package com.kookmin.lyl.module.product.repository;

import com.kookmin.lyl.module.product.dto.ProductDetails;

import java.util.List;

public interface ProductCacheRepository {
   public boolean add(String memberId, ProductDetails productDetails);
   public boolean addTop10Products(List<ProductDetails> productDetailsList);
   public List<ProductDetails> findRecentSearchedProducts(String memberId);
   public List<ProductDetails> findTop10Products();
}
