package com.kookmin.lyl.module.product.dto;

import com.kookmin.lyl.module.product.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDetails {
    private Long productNumber;
    private String name;
    private Integer price;
    private String manufacturer;
    private String origin;
    private Long categoryId;
    private Long shopId;

    //TODO:: 지연로딩때문에 페치 조인 전략으로 바꿀 필요가 있음
    public ProductDetails(Product product) {
        this.productNumber = product.getProductNumber();
        this.name = product.getName();
        this.price = product.getPrice();
        this.manufacturer = product.getManufacturer();
        this.origin = product.getOrigin();
        this.categoryId = product.getCategory().getId();
        this.shopId = product.getShop().getShopNumber();
    }
}
