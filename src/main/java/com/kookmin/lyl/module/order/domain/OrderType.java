package com.kookmin.lyl.module.order.domain;

public enum OrderType {
    // 송금(계좌이체), 무통장입금, 신용카드
    WIRE,
    WO_PASSBOOK,
    CREDIT_CARD
}
