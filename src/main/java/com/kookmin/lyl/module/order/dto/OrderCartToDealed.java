package com.kookmin.lyl.module.order.dto;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.order.domain.Order;
import lombok.Data;

@Data
public class OrderCartToDealed {
    private String senderAddress;
    private String senderEmail;
    private String senderPhone;
    private String senderName;
    private String request;

    private String receiverAddress;
    private String receiverEmail;
    private String receiverPhone;
    private String receiverName;

}
