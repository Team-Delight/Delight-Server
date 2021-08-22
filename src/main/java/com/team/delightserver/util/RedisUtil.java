package com.team.delightserver.util;

import com.team.delightserver.util.enumclass.CacheKey;
import com.team.delightserver.web.domain.food.RedisCacheFood;
import com.team.delightserver.web.dto.response.RecommendationRankResponse;
import java.util.List;
import javax.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Created by Bloo
 * @Date: 2021/08/20
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RedisUtil {

    @Resource (name = "redisTemplate")
    private ListOperations<String, RedisCacheFood> listOperations;
    @Resource (name = "redisTemplate")
    private ListOperations<String, RecommendationRankResponse> listRankOperations;
    private final RedisTemplate<String, Object> redisTemplate;
    private static final int CACHE_FOOD_START_NUMBER = 0;
    private static final int CACHE_FOOD_END_NUMBER = 121;

    public List<RedisCacheFood> getRedisCacheFoods() {
        return listOperations.range(CacheKey.RANDOM_FOODS_KEY, CACHE_FOOD_START_NUMBER, CACHE_FOOD_END_NUMBER);
    }

    public List<RecommendationRankResponse> getRedisRecommendationRankings() {
        return listRankOperations.range(CacheKey.RECOMMENDATION_RANKING_KEY, 0, 1);
    }

    public List<RecommendationRankResponse> getRedisRecommendationRankingsByCategoryId( Long categoryId ) {
        Long endNumber = listRankOperations.size(CacheKey.RECOMMENDATION_RANKING_KEY + categoryId);

        if (endNumber == 0) {
           throw new RuntimeException("해당 카테고리의 캐시 데이터가 존재하지 않습니다.");
        }

        return listRankOperations.range(CacheKey.RECOMMENDATION_RANKING_KEY + categoryId, 0, endNumber);
    }

    public void setRedisCacheFoods(List<RedisCacheFood> cacheFoods){
        for (RedisCacheFood cacheFood : cacheFoods) {
            listOperations.rightPush(CacheKey.RANDOM_FOODS_KEY, cacheFood);
        }
    }

    public void setRedisRankingResponseByCategoryId(List<RecommendationRankResponse> recommendationRankResponses, long id){
        for (RecommendationRankResponse rankResponse : recommendationRankResponses) {
            listRankOperations.rightPush(CacheKey.RECOMMENDATION_RANKING_KEY + id, rankResponse);
        }
    }

    public void deleteRedisCacheFoods() {
        redisTemplate.delete(CacheKey.RANDOM_FOODS_KEY);
    }

    public void deleteRedisCacheRankings() {
        log.info("======== Delete Ranking Cache Data =============");
        redisTemplate.delete(CacheKey.RECOMMENDATION_RANKING_KEY);
    }

}
