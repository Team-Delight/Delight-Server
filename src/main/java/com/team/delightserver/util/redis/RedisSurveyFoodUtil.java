package com.team.delightserver.util.redis;

import com.team.delightserver.util.enumclass.CacheKey;
import com.team.delightserver.web.domain.food.RedisCacheFood;
import java.util.List;
import javax.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Created by Bloo
 * @Date: 2021/08/22
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RedisSurveyFoodUtil {

    @Resource (name = "redisTemplate")
    private ListOperations<String, RedisCacheFood> cacheFoodsOperations;
    private final RedisTemplate<String, Object> redisTemplate;
    private static final int CACHE_FOOD_START_NUMBER = 0;

    public List<RedisCacheFood> getRedisCacheFoods() {
        Long endNumber = cacheFoodsOperations.size(CacheKey.RANDOM_FOODS_KEY);
        return cacheFoodsOperations
            .range(CacheKey.RANDOM_FOODS_KEY, CACHE_FOOD_START_NUMBER, endNumber);
    }

    public void setRedisCacheFoods(List<RedisCacheFood> cacheFoods){
        for (RedisCacheFood cacheFood : cacheFoods) {
            cacheFoodsOperations.rightPush(CacheKey.RANDOM_FOODS_KEY, cacheFood);
        }
    }

    public void deleteRedisCacheFoods() {
        log.info("======== Delete Survey Food Cache Data =============");
        redisTemplate.delete(CacheKey.RANDOM_FOODS_KEY);
    }

}
