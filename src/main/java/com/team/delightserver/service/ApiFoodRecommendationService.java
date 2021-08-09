package com.team.delightserver.service;

import com.team.delightserver.web.domain.foodrecommendation.FoodRecommendationRepository;
import com.team.delightserver.web.dto.response.RecommendationRankResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @CreateBy:Min
 * @Date: 2021/08/04
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiFoodRecommendationService {

    private final FoodRecommendationRepository foodRecommendationRepository;

    /**
     * 카테고리별 추천 랭킹 조회
     */
    @Transactional(readOnly = true)
    public List<RecommendationRankResponse> findTopTenFoodsByCategory(Long id) {
        return foodRecommendationRepository.findAllTopTenByCategoryId(id);
    }
}
