package com.kookmin.lyl.module.category.repository;

import com.kookmin.lyl.module.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
