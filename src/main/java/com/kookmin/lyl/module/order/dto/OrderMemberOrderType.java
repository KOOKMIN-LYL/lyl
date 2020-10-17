package com.kookmin.lyl.module.order.dto;

import com.kookmin.lyl.module.order.domain.OrderType;
import lombok.Data;

@Data
public class OrderMemberOrderType {
    private OrderType orderType;
    private String memberId;
}
