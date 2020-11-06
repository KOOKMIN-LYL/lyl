package com.kookmin.lyl.module.category.service;

import com.kookmin.lyl.builder.CategoryBuilder;
import com.kookmin.lyl.module.category.dto.CategoryCreateInfo;
import com.kookmin.lyl.module.category.dto.CategoryDetails;
import com.kookmin.lyl.module.category.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryServiceImplTest {
    @Autowired CategoryService categoryService;
    @Autowired CategoryRepository categoryRepository;
    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach()
    public void setUp() {

    }

    @Test
    @DisplayName("createCategory 성공 테스트 (최초 등록시)")
    public void createCategory_success_test() {
        CategoryCreateInfo categoryCreateInfo = CategoryBuilder.getCategoryCreateInfo();

        int count = categoryRepository.findAll().size();
        Long categoryId = categoryService.createCategory(categoryCreateInfo);

        CategoryDetails categoryDetails = categoryService.findCategory(categoryId);

        assertThat(categoryDetails)
                .hasFieldOrPropertyWithValue("id", categoryId)
                .hasFieldOrPropertyWithValue("name", categoryCreateInfo.getName())
                .hasFieldOrPropertyWithValue("sortOrder", (long)count+1);

    }
}