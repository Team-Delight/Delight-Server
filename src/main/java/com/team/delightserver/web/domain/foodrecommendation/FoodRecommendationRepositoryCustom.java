package com.team.delightserver.web.domain.foodrecommendation;

import com.team.delightserver.web.dto.response.RecommendationRankResponse;

import java.util.List;

/**
 * @CreateBy:Min
 * @Date: 2021/08/04
 */

public interface FoodRecommendationRepositoryCustom {

    List<RecommendationRankResponse> findAllTopTenByCategoryId(Long id);
}
