package com.kookmin.lyl.module.order.service;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.order.domain.Order;
import com.kookmin.lyl.module.order.domain.OrderProduct;
import com.kookmin.lyl.module.order.domain.OrderStatus;
import com.kookmin.lyl.module.order.repository.OrderProductRepository;
import com.kookmin.lyl.module.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class OrderService {
    @Autowired OrderRepository orderRepository;
    @Autowired OrderProductRepository orderProductRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //==생성 메서드==//
    public static Order createOrder(Member member, OrderProduct... orderProducts) {

        Order order = new Order();
        for (OrderProduct orderProduct : orderProducts) {
            order.addOrderProduct(orderProduct);
        }
        order.setStatus(OrderStatus.PENDING);
        return order;
    }
}
