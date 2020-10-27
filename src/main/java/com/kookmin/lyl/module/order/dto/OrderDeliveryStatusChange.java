package com.kookmin.lyl.module.order.dto;

import com.kookmin.lyl.module.order.domain.Order;
import com.kookmin.lyl.module.order.domain.OrderDeliveryStatus;
import com.kookmin.lyl.module.order.domain.OrderType;
import com.kookmin.lyl.module.order.repository.OrderRepository;
import lombok.Data;

// 배송 현황 정보 변경이다
@Data
public class OrderDeliveryStatusChange {
    private OrderDeliveryStatus status;
    public OrderDeliveryStatusChange(Order order){
        order.setStatus(status);
    }
}
