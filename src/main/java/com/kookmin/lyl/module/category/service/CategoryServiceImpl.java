package com.kookmin.lyl.module.category.service;

import com.kookmin.lyl.module.category.dto.CategoryCreateInfo;
import com.kookmin.lyl.module.category.dto.CategoryDetails;
import com.kookmin.lyl.module.category.dto.CategoryEditInfo;
import com.kookmin.lyl.module.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public Long createCategory(@NonNull CategoryCreateInfo categoryCreateInfo) {
        return null;
    }

    @Override
    public void editCategory(@NonNull Long categoryId, @NonNull CategoryEditInfo categoryEditInfo) {

    }

    @Override
    public void deleteCategory(@NonNull Long categoryId) {

    }

    @Override
    public CategoryDetails findCategory(@NonNull Long categoryId) {
        return null;
    }

    @Override
    public List<CategoryDetails> findCategories(@NonNull Long shopNumber) {
        return null;
    }
}
