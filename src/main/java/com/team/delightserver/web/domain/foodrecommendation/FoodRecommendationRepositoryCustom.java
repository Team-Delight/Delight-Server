package com.team.delightserver.web.domain.foodrecommendation;

import java.util.List;

public interface FoodRecommendationRepositoryCustom {
    List<FoodRecommendation> findAllTopTenByCategoryId();
}
