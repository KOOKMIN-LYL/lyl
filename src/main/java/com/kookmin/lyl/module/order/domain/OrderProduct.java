package com.kookmin.lyl.module.order.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

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

    @Column(name = "PRODUCT_OPTION_ID")
    private Long productOptionId;

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

    @Builder
    public OrderProduct(Long productId, Long productOptionId, String productName, int productPrice, int quantity,
                        String productOptions, Order order) {
        this.productId = productId;
        this.productOptionId = productOptionId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.productOptions = productOptions;
        eidtOrder(order);
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void eidtOrder(Order order) {
        if(order != null) {
            this.order = order;
        }
    }
}
