package com.kookmin.lyl.module.product.dto;

import com.kookmin.lyl.module.category.domain.Category;
import com.kookmin.lyl.module.product.domain.ProductOption;
import com.kookmin.lyl.module.product.domain.ProductStatus;
import com.kookmin.lyl.module.shop.domain.Shop;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductCreateInfo {
    private String name;
    private Integer price;
    private String manufacturer;
    private String origin;
    private Long categoryId;
    private Long shopId;
}
