package com.kookmin.lyl.module.order.repository;

import com.kookmin.lyl.module.member.domain.Member;
import com.kookmin.lyl.module.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findAll();
    List<Member> findByName(String name);
}
