package com.kookmin.lyl.module.order.repository;

import com.kookmin.lyl.module.order.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    OrderProduct save(OrderProduct orderProduct);
    List<OrderProduct> findByProductNumber(Long productNumber);
    List<OrderProduct> findByProductNameContaining(String productName); // 문자를 포함하고 있는 것을 내 놓는다.
    List<OrderProduct> findByProductPriceGreaterThanOrderByProductPrice(int price); // 일정 금액 이상 (오름순)
    List<OrderProduct> findByProductPriceLessThanOrderByProductPriceDesc(int price); // 일정 금액 이하 (내림순)
    // TODO: 이게 아닌 거 같은데 조건만 따로 빼서 하는게 있지 않았나
    List<OrderProduct> findAllOrderByProductPrice();

}
