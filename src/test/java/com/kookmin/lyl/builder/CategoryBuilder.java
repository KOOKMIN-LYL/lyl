package com.kookmin.lyl.builder;

import com.kookmin.lyl.module.category.dto.CategoryCreateInfo;

import java.util.Random;

public class CategoryBuilder {
    private static Random random;

    static {
        long seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    public static CategoryCreateInfo getCategoryCreateInfo() {
        long value = random.nextLong();

        CategoryCreateInfo categoryCreateInfo = new CategoryCreateInfo();
        categoryCreateInfo.setName("category" + value);

        return categoryCreateInfo;
    }
}
