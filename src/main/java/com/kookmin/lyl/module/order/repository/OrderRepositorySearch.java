package com.kookmin.lyl.module.order.repository;

import com.kookmin.lyl.module.order.dto.OrderDetails;
import com.kookmin.lyl.module.order.dto.OrderSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositorySearch {
    Page<OrderDetails> searchOrderDetails(Pageable pageable, OrderSearchCondition condition);
}
