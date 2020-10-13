package com.kookmin.lyl.module.product.dto;

import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.domain.ProductOption;
import com.kookmin.lyl.module.product.domain.ProductStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDetails {
    private Long productNumber;
    private String name;
    private Integer price;
    private String manufacturer;
    private String origin;
    private ProductStatus status;
    private Long categoryId;
    private Long shopId;
    private List<ProductOptionDetails> productOptionDetails;

    @QueryProjection
    public ProductDetails(Long productNumber, String name, Integer price, String manufacturer, String origin,
                          ProductStatus status, Long categoryId, Long shopId) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.origin = origin;
        this.status = status;
        this.categoryId = categoryId;
        this.shopId = shopId;
    }

    //TODO:: 지연로딩때문에 페치 조인 전략으로 바꿀 필요가 있음
    public ProductDetails(Product product) {
        this.productNumber = product.getProductNumber();
        this.name = product.getName();
        this.price = product.getPrice();
        this.manufacturer = product.getManufacturer();
        this.origin = product.getOrigin();
        this.status = product.getStatus();
        this.categoryId = product.getCategory().getId();
        this.shopId = product.getShop().getShopNumber();
    }
}
