package com.kookmin.lyl.module.order.dto;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.member.dto.MemberDetails;
import com.kookmin.lyl.module.order.domain.OrderType;
import com.kookmin.lyl.module.product.domain.ProductOption;
import com.kookmin.lyl.module.shop.domain.Shop;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderProductCart {
    private OrderType orderType;
    private String memberId;
}
