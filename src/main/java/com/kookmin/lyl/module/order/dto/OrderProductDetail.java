package com.kookmin.lyl.module.order.dto;

import com.kookmin.lyl.module.order.domain.Order;
import com.kookmin.lyl.module.order.domain.OrderProduct;
import com.kookmin.lyl.module.product.domain.ProductOption;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderProductDetail {
    private Long orderProductId;
    private String option;
    private String productName;
    private Long productNumber;
    private int productPrice;
    private int quantity;
    private Order order;
    private List<ProductOption> orderProductOptions  = new ArrayList<ProductOption>();

    public OrderProductDetail(OrderProduct orderProduct){
        this.orderProductId = orderProduct.getOrderProductId();
        this.option = orderProduct.getOption();
        this.productName = orderProduct.getProductName();
        this.productNumber = orderProduct.getProductNumber();
        this.productPrice = orderProduct.getProductPrice();
        this.quantity = orderProduct.getQuantity();
        this.order = orderProduct.getOrder();
        this.orderProductOptions = orderProduct.getOrderProductOptions();
    }
}
