package com.kookmin.lyl.module.product.repository;

import com.kookmin.lyl.module.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
