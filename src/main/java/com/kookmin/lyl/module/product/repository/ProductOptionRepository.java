package com.kookmin.lyl.module.product.repository;

import com.kookmin.lyl.module.product.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
    List<ProductOption> findByProductNumber(Long productNumber);
}
