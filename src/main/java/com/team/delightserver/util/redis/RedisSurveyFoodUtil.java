package com.team.delightserver.util.redis;

import com.team.delightserver.util.CustomListUtil;
import com.team.delightserver.util.enumclass.RedisCommonConstant;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.food.FoodRepository;
import com.team.delightserver.web.domain.food.RedisCacheFood;
import java.util.List;
import java.util.stream.Collectors;
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
    private final FoodRepository foodRepository;

    public List<RedisCacheFood> findRedisCacheFoodsByCategoryId (Long categoryId) {
        Long endNumber = cacheFoodsOperations.size(RedisCommonConstant.RANDOM_FOODS_KEY + categoryId);
        return cacheFoodsOperations
            .range(RedisCommonConstant.RANDOM_FOODS_KEY + categoryId, RedisCommonConstant.CACHE_FOOD_START_NUMBER, endNumber);
    }

    public void deleteAllRedisCacheFoods () {
        log.info("======== Delete Survey Food Cache Data =============");
        List<Long> categoryIds = CustomListUtil.setCategoryIdsAndReturnIds();
        for (Long categoryId : categoryIds) {
            redisTemplate.delete(RedisCommonConstant.RANDOM_FOODS_KEY +categoryId);
        }
    }

    public void setRedisSurveyFoods(Long categoryId) {
        List<Food> surveyFoodsByCategoryId = foodRepository.findAllByCategoryId(categoryId);
        List<RedisCacheFood> cacheFoods = surveyFoodsByCategoryId.stream()
            .map(Food::toRedisCacheFood)
            .collect(Collectors.toList());

        for (RedisCacheFood cacheFood : cacheFoods) {
            cacheFoodsOperations.rightPush(RedisCommonConstant.RANDOM_FOODS_KEY + categoryId, cacheFood);
        }
    }

    public void setCacheFoodByCategoryId() {
        List<Long> categoryIds = CustomListUtil.setCategoryIdsAndReturnIds();
        for (Long categoryId : categoryIds) {
            this.setRedisSurveyFoods(categoryId);
        }
    }
}
