package com.kookmin.lyl.module.order.dto;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.order.domain.Order;
import com.kookmin.lyl.module.order.domain.OrderDeliveryStatus;
import com.kookmin.lyl.module.order.domain.OrderProduct;
import com.kookmin.lyl.module.order.domain.OrderType;
import com.kookmin.lyl.module.shop.domain.Shop;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDetail {
    private Long orderId;
    private String request;
    private String deliveryAddress;
    private LocalDateTime orderedAt;
    private Integer totalPrice;
    private OrderType orderType;
    private Member member;
    private Shop shop;
    private OrderDeliveryStatus status;
    private List<OrderProduct> orderProducts;

    public OrderDetail(Order order){
        this.orderId = order.getOrderId();
        this.request = order.getRequest();
        this.deliveryAddress = order.getDeliveryAddress();
        this.orderedAt = order.getOrderedAt();
        this.orderType = order.getOrderType();
        this.totalPrice = order.getTotalPrice();
        this.member = order.getMember();
        this.shop = order.getShop();
        this.status = order.getStatus();
        this.orderProducts = order.getOrderProducts();
    }
}
