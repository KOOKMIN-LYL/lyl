package com.kookmin.lyl.module.order.domain;

import com.kookmin.lyl.module.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name="Order")
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

    // N:1 의 관계. 1명의 Member는 여러 Order을 한다
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // 1:N 의 관계. 1개의 Order에는 1개 이상의 product들이 있다
    // 여러 개의 product들을 List로 가져온다
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();

}
