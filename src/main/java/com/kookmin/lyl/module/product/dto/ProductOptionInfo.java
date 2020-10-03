package com.kookmin.lyl.module.product.dto;

import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.domain.ProductOptionType;
import lombok.Data;

import javax.persistence.*;

@Data
public class ProductOptionInfo {
    private Long id;
    private String option;
    private ProductOptionType type;
    private Long productNumber;
}
