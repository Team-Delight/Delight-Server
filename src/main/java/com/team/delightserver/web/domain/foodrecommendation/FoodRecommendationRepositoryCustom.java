package com.team.delightserver.web.domain.foodrecommendation;

import com.team.delightserver.web.dto.response.RankRecommendationsResponseDto;
import com.team.delightserver.web.dto.response.TopTenFoodCategoryResponseDto;

import java.util.List;

/**
 * @CreateBy:Min
 * @Date: 2021/08/04
 */

public interface FoodRecommendationRepositoryCustom {

    List<RankRecommendationsResponseDto> findTopTenFoods();

    List<TopTenFoodCategoryResponseDto> findAllTopTenByCategoryId(Long id);
}
