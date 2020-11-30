package com.kookmin.lyl.module.order.mapper;

import com.kookmin.lyl.module.order.dto.ProductStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderProductMapper {
    List<ProductStatistics> selectTop10Products();
}
