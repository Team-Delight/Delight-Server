package com.team.delightserver.service;

import com.team.delightserver.util.redis.RedisRecommendationRankUtil;
import com.team.delightserver.web.dto.response.RecommendationRankResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @CreateBy:Min
 * @Date: 2021/08/04
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiRecommendationService {

    private final RedisRecommendationRankUtil recommendationRankRedisUtil;

    @Transactional(readOnly = true)
    public List<RecommendationRankResponse> findTopTenFoodsByCategory(Long categoryId) {
        return recommendationRankRedisUtil.getRedisRecommendationRankingsByCategoryId(categoryId);
    }
}
