package com.kookmin.lyl.module.order.repository;

import com.kookmin.lyl.module.order.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    OrderProduct findByOrderIdAndProductIdAndProductOptionId(Long orderId, Long productId, Long productOptionId);
}
