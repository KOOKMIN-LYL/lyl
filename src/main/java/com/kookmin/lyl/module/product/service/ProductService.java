package com.kookmin.lyl.module.product.service;

import com.kookmin.lyl.infra.util.SearchCondition;
import com.kookmin.lyl.module.product.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    public Long createProduct(ProductCreateInfo productCreateInfo);
    public void editProduct(Long productNumber, ProductEditInfo productEditInfo);
    public void stopSellingProduct(Long productNumber);
    public void sellingProduct(Long productNumber);
    public void deleteProduct(Long productNumber);
    public ProductDetails findProduct(Long productNumber);
    public List<ProductDetails> searchProducts(Pageable pageable, SearchCondition searchCondition);
    public void addProductOption(Long productNumber, ProductOptionInfo productOptionInfo);
    public void editProductOption(Long productNumber, Long productOptionId, ProductOptionInfo productOptionInfo);
    public void deleteProductOption(Long productNumber, Long productOptionId);
    public ProductOptionDetails findProductOption(Long productOptionId);
    public List<ProductOptionDetails> findProductOptions(Long ProductNumber);
}
