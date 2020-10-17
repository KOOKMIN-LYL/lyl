package com.kookmin.lyl.module.order.dto;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.member.dto.MemberDetails;
import com.kookmin.lyl.module.order.domain.OrderType;
import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.shop.domain.Shop;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderProductWish {
    private OrderType orderType;
    private int orderId;                    // 여기서는 관심상품으로 등록이라 0으로 할거야[
    private MemberDetails memberDetails;    // 기본 정보들 들어있는거. 어차피 주문시에 넣어서 동일하게 가져와야됨.
}
