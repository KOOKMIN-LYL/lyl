package com.kookmin.lyl.module.product.dto;

import lombok.Data;

@Data
public class ProductEditInfo {
    private String name;
    private Integer price;
    private String manufacturer;
    private String origin;
}
