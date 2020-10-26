package com.kookmin.lyl.module.order.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name="ORDER_PRODUCT")
public class OrderProduct {
    @Id @GeneratedValue
    @Column(name = "ORDER_PRODUCT_ID")
    private Long id;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_PRICE")
    private int productPrice;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRODUCT_OPTION")
    private String productOptions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

}
