package com.kookmin.lyl.module.order.dto;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class OrderProductInfo {
    private Long productId;
    private Long productOptionId;
    private int quantity;
}
