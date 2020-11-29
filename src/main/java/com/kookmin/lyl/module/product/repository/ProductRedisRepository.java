package com.kookmin.lyl.module.product.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kookmin.lyl.module.product.dto.ProductDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class ProductRedisRepository implements ProductCacheRepository{
    private static int DEFAULT_SEARCH_SIZE = 10;
    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;

    @Override
    public boolean add(String memberId, ProductDetails productDetails) {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();

        try {
            long size = zSet.zCard(memberId) + 1L;
            zSet.add(memberId, objectMapper.writeValueAsString(productDetails), size);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public List<ProductDetails> findRecentSearchedProducts(String memberId) {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();

        Set<String> recentSearchedProducts =  zSet.reverseRange(memberId, 0, DEFAULT_SEARCH_SIZE -1);

        List<ProductDetails> productDetailsList = new ArrayList<>();

        try {
            for (String product : recentSearchedProducts) {
                ProductDetails productDetails = objectMapper.readValue(product, ProductDetails.class);
                productDetailsList.add(productDetails);
            }
        } catch (JsonProcessingException exception) {
            //TODO:: JsonProcessingException 발생시 처리 할 방법
            exception.printStackTrace();
            return new ArrayList<ProductDetails>();
        }

        return productDetailsList;
    }
}
