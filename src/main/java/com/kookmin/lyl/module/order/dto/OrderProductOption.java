package com.kookmin.lyl.module.order.dto;

import com.kookmin.lyl.module.product.domain.ProductOption;
import com.kookmin.lyl.module.product.domain.ProductOptionType;
import com.kookmin.lyl.module.product.domain.ProductStatus;
import lombok.Data;

@Data
public class OrderProductOption {
    private Long productNumber;             // 상품 번호
    private ProductStatus productStatus;    // 현재 판매중인지 보던거
    private ProductOption productOption;
    private ProductOptionType productOptionType;
}
