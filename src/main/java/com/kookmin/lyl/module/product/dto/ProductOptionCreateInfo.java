package com.kookmin.lyl.module.product.dto;

import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.domain.ProductOptionType;
import lombok.Data;

import javax.persistence.*;

@Data
public class ProductOptionCreateInfo {
    private String option;
    private String type;
    private Long productNumber;
}
