package com.team.delightserver.util.redis;

import com.team.delightserver.util.enumclass.CacheKey;
import com.team.delightserver.web.domain.recommendation.RecommendationRepository;
import com.team.delightserver.web.dto.response.RecommendationRankResponse;
import java.util.List;
import java.util.Objects;
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
public class RedisRecommendationRankUtil {

    private final RecommendationRepository recommendationRepository;

    private static final long CATEGORY_ALL_ID = 0;
    private static final long CATEGORY_KOREA_ID = 1;
    private static final long CATEGORY_JAPANESE_ID = 2;
    private static final long CATEGORY_CHINESE_ID = 3;
    private static final long CATEGORY_WESTERN_ID = 4;
    private static final long CATEGORY_FAST_FOOD_ID = 5;
    private static final long CATEGORY_SNACK_ID = 6;

    @Resource (name = "redisTemplate")
    private ListOperations<String, RecommendationRankResponse> listRankOperations;
    private final RedisTemplate<String, Object> redisTemplate;

    public boolean isExistRecommendationRankings () {
        List<RecommendationRankResponse> res = listRankOperations
            .range(CacheKey.RECOMMENDATION_RANKING_KEY + CATEGORY_ALL_ID, 0, 1);
        return Objects.requireNonNull(res).size() > 0;
    }

    public List<RecommendationRankResponse> getRedisRecommendationRankingsByCategoryId ( Long categoryId ) {
        Long endNumber = listRankOperations.size(CacheKey.RECOMMENDATION_RANKING_KEY + categoryId);
        if ( endNumber == 0 ) {
            throw new RuntimeException("해당 카테고리의 캐시 데이터가 존재하지 않습니다.");
        }
        return listRankOperations
            .range(CacheKey.RECOMMENDATION_RANKING_KEY + categoryId, 0, endNumber);
    }


    public void setRedisRankingResponseByCategoryId (
        List<RecommendationRankResponse> recommendationRankResponses, long id ) {
        for ( RecommendationRankResponse rankResponse : recommendationRankResponses ) {
            listRankOperations.rightPush(CacheKey.RECOMMENDATION_RANKING_KEY + id, rankResponse);
        }
    }

    public void deleteAllRedisCacheRankings () {
        log.info("======== Delete Ranking Cache Data =============");
        redisTemplate.delete(CacheKey.RECOMMENDATION_RANKING_KEY + CATEGORY_ALL_ID);
        redisTemplate.delete(CacheKey.RECOMMENDATION_RANKING_KEY + CATEGORY_KOREA_ID);
        redisTemplate.delete(CacheKey.RECOMMENDATION_RANKING_KEY + CATEGORY_JAPANESE_ID);
        redisTemplate.delete(CacheKey.RECOMMENDATION_RANKING_KEY + CATEGORY_CHINESE_ID);
        redisTemplate.delete(CacheKey.RECOMMENDATION_RANKING_KEY + CATEGORY_WESTERN_ID);
        redisTemplate.delete(CacheKey.RECOMMENDATION_RANKING_KEY + CATEGORY_FAST_FOOD_ID);
        redisTemplate.delete(CacheKey.RECOMMENDATION_RANKING_KEY + CATEGORY_SNACK_ID);
    }

    public void setRanking () {
        log.info("================ Set Ranking Start ==========");
        this.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(CATEGORY_ALL_ID), CATEGORY_ALL_ID
        );

        this.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(CATEGORY_KOREA_ID), CATEGORY_KOREA_ID
        );

        this.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(CATEGORY_JAPANESE_ID), CATEGORY_JAPANESE_ID
        );

        this.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(CATEGORY_CHINESE_ID), CATEGORY_CHINESE_ID
        );

        this.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(CATEGORY_WESTERN_ID), CATEGORY_WESTERN_ID
        );

        this.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(CATEGORY_FAST_FOOD_ID), CATEGORY_FAST_FOOD_ID
        );

        this.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(CATEGORY_SNACK_ID), CATEGORY_SNACK_ID
        );
    }
}
