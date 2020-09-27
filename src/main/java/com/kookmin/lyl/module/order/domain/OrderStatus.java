package com.kookmin.lyl.module.order.domain;

public enum OrderStatus {
    // 준비중, 지연됨, 취소됨, 출고됨, 배송중, 배송완료
    READY,
    DELAYED,
    CANCELLED,
    SHIPPED,
    IN_TRANSIT,
    DELIVERED
}
