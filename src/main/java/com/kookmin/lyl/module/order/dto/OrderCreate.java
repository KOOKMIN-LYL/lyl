package com.kookmin.lyl.module.order.dto;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.order.domain.OrderDeliveryStatus;
import com.kookmin.lyl.module.order.domain.OrderProduct;
import com.kookmin.lyl.module.order.domain.OrderType;
import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.shop.domain.Shop;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderCreate {
    private OrderType orderType;    // 일단 wish, cart, dealed 를 확인을 하기 위해서 일단 이거 먼저 확인을 하는 것이다
    private LocalDateTime orderedAt;    // 이거는 그래 그냥 바로 생성을 하고
    private Member member;          // 필수고
    private Product product;
    private String request;         // 요청사항도 일단은 바로 결제를 하는 경우에 들어가는 거고
    private String deliveryAddress; // 주소 새로 입력을 할거면 이거를 하는 거고
    private Integer totalPrice;     // 총액을 구하는거 근데 이거는 나중에 함수로 빼야 하지 않을까 일단 하자
    private OrderProduct orderProduct;

}
