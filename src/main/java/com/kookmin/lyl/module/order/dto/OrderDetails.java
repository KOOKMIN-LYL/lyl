package com.kookmin.lyl.module.order.dto;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.order.domain.Order;
import com.kookmin.lyl.module.order.domain.OrderProduct;
import com.kookmin.lyl.module.order.domain.OrderStatus;
import com.kookmin.lyl.module.order.domain.OrderType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDetails {
    private Long id;
    private int totalPrice;
    private String deliveryAddress;
    private String request;
    private String orderStatus;
    private String orderType;
    private List<OrderProduct> orderProducts;

    public OrderDetails(Order order) {
        this.id = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.deliveryAddress = order.getDeliveryAddress();
        this.request = order.getRequest();
        this.orderStatus = order.getStatus().toString();
        this.orderType = order.getOrderType().toString();
    }

    @QueryProjection
    public OrderDetails(Long id, int totalPrice, String deliveryAddress, String request, String orderStatus, String orderType) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.deliveryAddress = deliveryAddress;
        this.request = request;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
    }
}
