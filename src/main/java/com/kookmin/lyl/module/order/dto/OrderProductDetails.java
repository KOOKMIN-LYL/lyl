package com.kookmin.lyl.module.order.dto;

import com.kookmin.lyl.module.order.domain.Order;
import com.kookmin.lyl.module.order.domain.OrderProduct;
import lombok.Data;

import javax.persistence.*;

@Data
public class OrderProductDetails {
    private Long id;
    private Long productId;
    private Long productOptionId;
    private String productName;
    private int productPrice;
    private int quantity;
    private String productOptions;

    public OrderProductDetails(OrderProduct orderProduct) {
        this.id = orderProduct.getId();
        this.productId = orderProduct.getProductId();
        this.productOptionId = orderProduct.getProductOptionId();
        this.productName = orderProduct.getProductName();
        this.productPrice = orderProduct.getProductPrice();
        this.quantity = orderProduct.getQuantity();
        this.productOptions = orderProduct.getProductOptions();
    }

}
