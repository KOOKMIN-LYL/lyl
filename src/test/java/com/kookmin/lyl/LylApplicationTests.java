package com.kookmin.lyl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kookmin.lyl.module.order.dto.ProductStatistics;
import com.kookmin.lyl.module.order.mapper.OrderProductMapper;
import com.kookmin.lyl.module.product.dto.ProductDetails;
import com.kookmin.lyl.module.product.service.ProductService;
import com.kookmin.lyl.module.product.service.ProductServiceWithCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class LylApplicationTests {
    @Autowired
    private OrderProductMapper mapper;

    @Test
    void test() throws JsonProcessingException {
        List<ProductStatistics> result = mapper.selectTop10Products();

        System.out.println(result.size());
    }

}
