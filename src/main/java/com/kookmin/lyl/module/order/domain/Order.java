package com.kookmin.lyl.module.order.domain;

import com.kookmin.lyl.module.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name="ORDERS")
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "TOTAL_PRICE")
    private int totalPrice;

    @Column(name = "DELIVERY_ADDRESS")
    private String deliveryAddress;

    @Column(name = "REQUEST")
    private String request;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS")
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_TYPE")
    private  OrderType orderType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USN")
    private Member member;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();

    @Builder
    public Order(OrderType orderType, Member member) {
        this.status = OrderStatus.READY;
        this.orderType = orderType;
        this.member = member;
    }

    public void editOrderStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
    }

    public void editOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public void editTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void editDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void editRequest(String request) {
        this.request = request;
    }

}
