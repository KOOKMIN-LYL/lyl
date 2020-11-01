package com.kookmin.lyl.module.order.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class OrderCartInfo {
    private String deliveryAddress;
    private String request;
    private List<OrderProductInfo> orderProductInfos;
}
