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

        Category latestCategory = categoryRepository.findFirstByOrderBySortOrderAsc();

        category.initSortOrder(latestCategory);

        category = categoryRepository.save(category);

        return category.getId();
    }

    @Override
    public void editCategory(@NonNull Long categoryId, @NonNull CategoryEditInfo categoryEditInfo) {

    }

    @Override
    public void deleteCategory(@NonNull Long categoryId) {

    }

    @Override
    public CategoryDetails findCategory(@NonNull Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
        return new CategoryDetails(category);
    }

    @Override
    public List<CategoryDetails> findCategories(@NonNull Long shopNumber) {
        return null;
    }
}
