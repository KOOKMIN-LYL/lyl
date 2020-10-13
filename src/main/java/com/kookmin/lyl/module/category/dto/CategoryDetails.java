package com.kookmin.lyl.module.category.dto;

import com.kookmin.lyl.module.category.domain.Category;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class CategoryDetails {
    private Long id;
    private String name;
    private Long sortOrder;

    public CategoryDetails(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.sortOrder = category.getSortOrder();
    }
}
