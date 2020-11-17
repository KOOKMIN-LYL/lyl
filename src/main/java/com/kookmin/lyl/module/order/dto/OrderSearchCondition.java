package com.kookmin.lyl.module.order.dto;

import lombok.Data;

@Data
public class OrderSearchCondition {
    private Long orderId;
    private String orderStatus;
    private String orderType;
    private String memberId;
}
