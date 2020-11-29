package com.kookmin.lyl.module.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("productStatistics")
public class ProductStatistics {
    private int count;
    private Long productId;
}
