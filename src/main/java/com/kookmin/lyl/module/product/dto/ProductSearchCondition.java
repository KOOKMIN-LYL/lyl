package com.kookmin.lyl.module.product.dto;

import com.kookmin.lyl.infra.util.SearchCondition;
import lombok.Data;

@Data
public class ProductSearchCondition extends SearchCondition {
    private Long categoryId;
}
