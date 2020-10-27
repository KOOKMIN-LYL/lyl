package com.kookmin.lyl.module.order.repository;

import com.kookmin.lyl.module.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositorySearch {
    List<Order> findByMemberIdAndOrderType(String memberId, String orderType);
}
