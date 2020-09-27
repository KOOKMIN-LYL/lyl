package com.kookmin.lyl.module.order.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name="ORDER_PRODUCT")
public class OrderProduct {
    @Id @GeneratedValue
    @Column(name = "ORDER_PRODUCT_ID")
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_PRICE")
    private int productPrice;

    @Column(name = "QUANTITY")
    private int quantity;

    // 1:N 인 관계 연결 부분
    @ManyToOne(fetch = FetchType.LAZY)
    // ORDER_ID 를 기준으로
    @JoinColumn(name = "ORDER_ID")
    private Order order;

}
