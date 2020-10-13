package com.kookmin.lyl.module.category.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name="CATEGORY")
public class Category {
    private final static Long DEFAULT_SORT_ORDER = 0L;

    @Id @GeneratedValue
    @Column(name="CATEGORY_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="SORT_ORDER")
    private Long sortOrder;

    @Builder
    public Category(String name) {
        this.name = name;
        this.sortOrder = DEFAULT_SORT_ORDER;
    }

    public void swapSortOrder(Category targetCategory) {
        long originSortOrder = this.sortOrder;
        this.sortOrder = targetCategory.sortOrder;
        targetCategory.sortOrder = originSortOrder;
    }

    public void initSortOrder(Category category) {
        if(category == null) {
            this.sortOrder = 1L;
        } else {
            this.sortOrder = category.getSortOrder() + 1L;
        }
    }

    public void editName(String name) {
        this.name = name;
    }
}
