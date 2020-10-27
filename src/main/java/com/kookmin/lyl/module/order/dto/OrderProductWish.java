package com.kookmin.lyl.module.order.dto;

import com.kookmin.lyl.module.member.dto.MemberDetails;
import com.kookmin.lyl.module.product.domain.Product;
import com.kookmin.lyl.module.product.domain.ProductOption;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderProductWish {
    private Product product;
    private MemberDetails memberDetails;
//    private List<ProductOption> productOptionList;
//    private String option;
}
