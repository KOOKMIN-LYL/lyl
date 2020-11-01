package com.kookmin.lyl.module.order.dto;

import com.kookmin.lyl.module.order.domain.DeliveryInformation;
import com.kookmin.lyl.module.order.domain.Order;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class OrderDetails {
    private Long id;
    private int totalPrice;
    private String deliveryAddress;
    private String request;
    private String orderStatus;
    private String orderType;
    private String orderedAt;
    private List<OrderProductDetails> orderProducts;
    private List<OrderDeliveryInfo> orderDeliveryInfos;

    public OrderDetails(Order order) {
        this.id = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.deliveryAddress = order.getDeliveryAddress();
        this.request = order.getRequest();
        this.orderStatus = order.getStatus().toString();
        this.orderType = order.getOrderType().toString();
        this.orderedAt = order.getOrderedAtToString();
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
