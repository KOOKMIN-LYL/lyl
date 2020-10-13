package com.kookmin.lyl.module.product.dto;

import com.kookmin.lyl.module.product.domain.ProductOption;
import com.kookmin.lyl.module.product.domain.ProductOptionType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductOptionDetails {
    private Long id;
    private String option;
    private String type;
    private Long productNumber;

    public ProductOptionDetails(ProductOption productOption) {
        this.id = productOption.getId();
        this.option = productOption.getOption();
        this.type = productOption.getType().toString();
        this.productNumber = productOption.getProduct().getProductNumber();
    }
}
