package com.kookmin.lyl.module.order.dto;

import com.kookmin.lyl.module.order.domain.DeliveryInformation;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDeliveryInfo {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String userType;

    public OrderDeliveryInfo(DeliveryInformation deliveryInformation) {
        this.name = deliveryInformation.getName();
        this.address = deliveryInformation.getAddress();
        this.phone = deliveryInformation.getPhone();
        this.email = deliveryInformation.getEmail();
        this.userType = deliveryInformation.getUserType().toString();
    }
}
