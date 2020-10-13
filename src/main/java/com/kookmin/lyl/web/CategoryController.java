package com.kookmin.lyl.web;

import com.kookmin.lyl.module.category.dto.CategoryDetails;
import com.kookmin.lyl.module.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(value = "/category")
    public Map<String, Object> getCategories() {
        Map<String, Object> result = new HashMap<>();
        result.put("category", categoryService.findCategories());
        return result;
    }
}
