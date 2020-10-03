package com.kookmin.lyl.module.category.service;

import com.kookmin.lyl.module.category.dto.CategoryCreateInfo;
import com.kookmin.lyl.module.category.dto.CategoryDetails;
import com.kookmin.lyl.module.category.dto.CategoryEditInfo;

import java.util.List;

public interface CategoryService {
    public Long createCategory(CategoryCreateInfo categoryCreateInfo);
    public void editCategory(Long categoryId, CategoryEditInfo categoryEditInfo);
    public void swapCategory(Long categoryId, Long targetCategoryId);
    public void deleteCategory(Long categoryId);
    public CategoryDetails findCategory(Long categoryId);
    public List<CategoryDetails> findCategories();
}
