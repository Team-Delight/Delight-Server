package com.team.delightserver.service;

import com.team.delightserver.util.RedisUtil;
import com.team.delightserver.web.domain.recommendation.RecommendationRepository;
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

    private final RecommendationRepository recommendationRepository;
    private final RedisUtil redisUtil;

//    @Transactional(readOnly = true)
//    public List<RecommendationRankResponse> findTopTenFoodsByCategory(Long categoryId) {
//        return recommendationRepository.findAllTopTenByCategoryId(categoryId);
//    }

    @Transactional(readOnly = true)
    public List<RecommendationRankResponse> findTopTenFoodsByCategory(Long categoryId) {
        return redisUtil.getRedisRecommendationRankingsByCategoryId(categoryId);
    }
}
