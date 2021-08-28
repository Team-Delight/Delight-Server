package com.team.delightserver.util.redis;

import com.team.delightserver.util.enumclass.RedisCommonConstant;
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


    @Resource (name = "redisTemplate")
    private ListOperations<String, RecommendationRankResponse> listRankOperations;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RecommendationRepository recommendationRepository;
    private static final int REDIS_RANKING_RESPONSE_START_NUMBER = 0;

    public List<RecommendationRankResponse> findRedisRecommendationRankingsByCategoryId ( Long categoryId ) {
        Long endNumber = listRankOperations.size(RedisCommonConstant.RECOMMENDATION_RANKING_KEY + categoryId);

        if ( endNumber == 0 ) {
            throw new RuntimeException("해당 카테고리의 캐시 데이터가 존재하지 않습니다.");
        }

        return listRankOperations.range(RedisCommonConstant.RECOMMENDATION_RANKING_KEY + categoryId,
            REDIS_RANKING_RESPONSE_START_NUMBER, endNumber);
    }


    public void setRedisRankingResponseByCategoryId ( List<RecommendationRankResponse> recommendationRankResponses, long id ) {
        for ( RecommendationRankResponse rankResponse : recommendationRankResponses ) {
            listRankOperations
                .rightPush(RedisCommonConstant.RECOMMENDATION_RANKING_KEY + id, rankResponse);
        }
    }

    public void deleteAllRedisCacheRankings () {
        log.info("======== Delete Ranking Cache Data =============");

        redisTemplate.delete(
            RedisCommonConstant.RECOMMENDATION_RANKING_KEY + RedisCommonConstant.CATEGORY_ALL_ID
        );

        redisTemplate.delete(
            RedisCommonConstant.RECOMMENDATION_RANKING_KEY + RedisCommonConstant.CATEGORY_KOREA_ID
        );

        redisTemplate.delete(
            RedisCommonConstant.RECOMMENDATION_RANKING_KEY + RedisCommonConstant.CATEGORY_JAPANESE_ID
        );

        redisTemplate.delete(
            RedisCommonConstant.RECOMMENDATION_RANKING_KEY + RedisCommonConstant.CATEGORY_CHINESE_ID);

        redisTemplate.delete(
            RedisCommonConstant.RECOMMENDATION_RANKING_KEY + RedisCommonConstant.CATEGORY_WESTERN_ID);

        redisTemplate.delete(
            RedisCommonConstant.RECOMMENDATION_RANKING_KEY + RedisCommonConstant.CATEGORY_SNACK_ID
        );
    }

    public void setRanking () {
        log.info("================ Set Ranking Start ==========");
        this.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(RedisCommonConstant.CATEGORY_KOREA_ID),
            RedisCommonConstant.CATEGORY_ALL_ID
        );

        this.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(RedisCommonConstant.CATEGORY_KOREA_ID),
            RedisCommonConstant.CATEGORY_KOREA_ID
        );

        this.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(RedisCommonConstant.CATEGORY_JAPANESE_ID),
            RedisCommonConstant.CATEGORY_JAPANESE_ID
        );

        this.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(RedisCommonConstant.CATEGORY_CHINESE_ID),
            RedisCommonConstant.CATEGORY_CHINESE_ID
        );

        this.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(RedisCommonConstant.CATEGORY_WESTERN_ID),
            RedisCommonConstant.CATEGORY_WESTERN_ID
        );

        this.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(RedisCommonConstant.CATEGORY_SNACK_ID),
            RedisCommonConstant.CATEGORY_SNACK_ID
        );
    }

    public boolean isExistRecommendationRankings () {
        List<RecommendationRankResponse> res = listRankOperations
            .range(RedisCommonConstant.RECOMMENDATION_RANKING_KEY + RedisCommonConstant.CATEGORY_ALL_ID, 0, 1);
        return Objects.requireNonNull(res).size() > 0;
    }
}
