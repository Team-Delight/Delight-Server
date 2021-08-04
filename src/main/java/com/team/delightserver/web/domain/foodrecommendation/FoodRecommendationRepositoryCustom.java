package com.team.delightserver.web.domain.foodrecommendation;

import com.querydsl.core.Tuple;
import com.team.delightserver.web.dto.response.TopTenFoodCategoryResponseDto;

import java.util.List;

public interface FoodRecommendationRepositoryCustom {
    List<TopTenFoodCategoryResponseDto> findAllTopTenByCategoryId(Long id);
}
