package com.kookmin.lyl.module.order.repository;

import com.kookmin.lyl.module.order.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    public OrderProduct findByOrderIdAndProductIdAndProductOptionId(Long orderId, Long productId, Long productOptionId);
    public List<OrderProduct> findByOrderId(Long orderId);
    public void deleteByProductIdAndProductOptionIdAndOrderId(Long productId, Long productOptionId, Long orderId);
}
