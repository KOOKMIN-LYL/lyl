package com.kookmin.lyl.module.order.domain;

public enum OrderStatus {
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
