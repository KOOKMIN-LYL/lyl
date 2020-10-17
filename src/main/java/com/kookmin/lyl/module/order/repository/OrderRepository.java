package com.kookmin.lyl.module.order.repository;

import com.kookmin.lyl.module.order.domain.Order;
import com.kookmin.lyl.module.order.domain.OrderDeliveryStatus;
import com.kookmin.lyl.module.order.domain.OrderType;
import com.kookmin.lyl.module.order.dto.OrderMemberOrderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Order save(Order order);
    List<Order> findOrderByOrderedAtOrderByOrderedAtDesc(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findOrderByStatusGroupByStatus(OrderDeliveryStatus status);
    List<Order> findByOrderTypeGroupByOrderType(OrderType orderType);
    List<Order> findByMemberIdAndOrderType(String memberId, String orderType);
}
