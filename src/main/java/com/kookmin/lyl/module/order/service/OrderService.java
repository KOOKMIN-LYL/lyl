package com.kookmin.lyl.module.order.service;

import com.kookmin.lyl.module.order.dto.*;

import java.util.List;

public interface OrderService {
    public void orderProductWish(OrderProductWish orderProductWish);
    public void orderProductCart(OrderProductCart orderProductCart);

    public void orderCartToDeal(OrderCartToDeal orderCartToDeal);

    public void orderProductCreate(OrderProductCreate orderProductCreate);
    public List<OrderDetail> findByMemberIdAndOrderType(OrderMemberOrderType orderMemberOrderType);
}
