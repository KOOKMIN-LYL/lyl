package com.kookmin.lyl.module.product.dto;

import com.kookmin.lyl.module.product.domain.ProductOptionType;
import lombok.Data;

@Data
public class ProductOptionEditInfo {
    private Long id;
    private String option;
    private String type;
    private Long productNumber;
}
