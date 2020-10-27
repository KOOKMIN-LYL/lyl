package com.kookmin.lyl.module.order.repository;

import com.kookmin.lyl.module.order.domain.Order;
import com.kookmin.lyl.module.order.domain.OrderDeliveryStatus;
import com.kookmin.lyl.module.order.domain.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Order save(Order order);
    Order findByOrderId(Long orderId);
//    List<Order> findByOrderId(Long orderId);
    Order findByMemberIdAndOrderType(String memberId, OrderType orderType);
    List<Order> findOrderByOrderedAtOrderByOrderedAtDesc(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findOrderByStatusGroupByStatus(OrderDeliveryStatus status);
    List<Order> findByOrderTypeGroupByOrderType(OrderType orderType);
    List<Order> findByMemberIdAndOrderType(String memberId, String orderType);

}
