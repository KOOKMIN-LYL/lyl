package com.kookmin.lyl.module.shop.repository;

import com.kookmin.lyl.module.shop.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
