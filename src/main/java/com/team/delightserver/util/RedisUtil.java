package com.team.delightserver.util;

import com.team.delightserver.util.enumclass.CacheKey;
import com.team.delightserver.web.domain.food.RedisCacheFood;
import java.util.List;
import javax.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Created by Bloo
 * @Date: 2021/08/20
 */
@RequiredArgsConstructor
@Component
public class RedisUtil {

    @Resource (name = "redisTemplate")
    private ListOperations<String, RedisCacheFood> listOperations;
    private final RedisTemplate<String, Object> redisTemplate;
    private static final int CACHE_FOOD_START_NUMBER = 0;
    private static final int CACHE_FOOD_END_NUMBER = 121;

    public List<RedisCacheFood> getRedisCacheFoods() {
        return listOperations.range(CacheKey.RANDOM_FOODS, CACHE_FOOD_START_NUMBER, CACHE_FOOD_END_NUMBER);
    }

    public void setRedisCacheFoods(List<RedisCacheFood> cacheFoods){
        for (RedisCacheFood cacheFood : cacheFoods) {
            listOperations.rightPush(CacheKey.RANDOM_FOODS, cacheFood);
        }
    }

    public void deleteRedisCacheFoods() {
        redisTemplate.delete(CacheKey.RANDOM_FOODS);
    }
}
