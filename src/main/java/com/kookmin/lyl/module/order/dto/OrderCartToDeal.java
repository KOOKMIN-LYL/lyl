package com.kookmin.lyl.module.order.dto;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.order.domain.Order;
import lombok.Data;

import java.util.List;

@Data
public class OrderCartToDeal {
//    private List<Order> ordersToDeal;   // 어차피 체크된 것 Order들이 올 것 아니여
    private Long orderId;
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
