package com.kookmin.lyl.module.order.dto;
import com.kookmin.lyl.module.order.domain.OrderProduct;
import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.domain.ProductOption;
import lombok.Data;

import java.util.List;

@Data
public class OrderProductCreate {
    private List<ProductOption> productOptionList;
    private String option;
    private Integer productQuantity;
    private Product product;
}
