package com.kookmin.lyl.module.order.service;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.order.domain.Order;
import com.kookmin.lyl.module.order.domain.OrderType;
import com.kookmin.lyl.module.order.dto.*;
import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.domain.ProductOption;

import java.util.List;

public interface OrderService {
    public void orderProductWish(Order order, OrderProductCreate orderProductCreate);
    public void orderProductCart(Order order, OrderProductCreate orderProductCreate, List<ProductOption> productOptions)
    public void orderCreate(OrderCreate orderCreate);
    public void cartToDealed(OrderCartToDealed orderCartToDealed, OrderMemberOrderType orderMemberOrderType);
    public void orderProductCreate(OrderProductCreate orderProductCreate, Product product);
    public List<OrderDetail> findByMemberIdAndOrderType(OrderMemberOrderType orderMemberOrderType);
}
