package com.kookmin.lyl.module.category.service;

import com.kookmin.lyl.module.category.domain.Category;
import com.kookmin.lyl.module.category.dto.CategoryCreateInfo;
import com.kookmin.lyl.module.category.dto.CategoryDetails;
import com.kookmin.lyl.module.category.dto.CategoryEditInfo;
import com.kookmin.lyl.module.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public Long createCategory(@NonNull CategoryCreateInfo categoryCreateInfo) {
        Category category = Category.builder()
                .name(categoryCreateInfo.getName())
                .build();

        Category latestCategory = categoryRepository.findFirstByOrderBySortOrderDesc();

        category.initSortOrder(latestCategory);

        category = categoryRepository.save(category);

        return category.getId();
    }

    @Override
    public void editCategory(@NonNull Long categoryId, @NonNull CategoryEditInfo categoryEditInfo) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);

        category.editName(categoryEditInfo.getName());
    }

    @Override
    public void swapCategory(Long categoryId, Long targetCategoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
        Category targetCategory = categoryRepository.findById(targetCategoryId).orElseThrow(EntityNotFoundException::new);

        category.swapSortOrder(targetCategory);
    }

    @Override
    public void deleteCategory(@NonNull Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryDetails findCategory(@NonNull Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
        return new CategoryDetails(category);
    }

    @Override
    public List<CategoryDetails> findCategories() {
        List<Category> categories =  categoryRepository.findAll();
        List<CategoryDetails> categoryDetails = new ArrayList<>();

        for(Category category : categories) {
            categoryDetails.add(new CategoryDetails(category));
        }

        return categoryDetails;
    }
}
