package com.kookmin.lyl.module.product.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("products")
public class ProductDetailsCache {
    @Id
    private String memberId;
    private Long productNumber;
    private String productDetails;
}
