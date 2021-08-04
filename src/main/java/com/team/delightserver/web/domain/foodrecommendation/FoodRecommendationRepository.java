package com.team.delightserver.web.domain.foodrecommendation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRecommendationRepository
        extends JpaRepository<FoodRecommendation, Long>, FoodRecommendationRepositoryCustom {

}