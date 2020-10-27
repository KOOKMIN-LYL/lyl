package com.kookmin.lyl.module.order.domain;

public enum OrderDeliveryStatus {
    // TODO::관심이나 장바구니때문에 Null 되게 해야 될 것 같다
    // 주문 들어감, 준비중, 결제 대기중, 지연됨, 취소됨, 출고됨, 배송중, 배송완료
    ORDERED,
    READY,
    PENDING,
    DELAYED,
    CANCELLED,
    SHIPPED,
    IN_TRANSIT,
    DELIVERED
}
