package com.team.delightserver.web.domain.foodrecommendation;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @CreateBy:Min
 * @Date: 2021/08/04
 */

public interface FoodRecommendationRepository
        extends JpaRepository<FoodRecommendation, Long>, FoodRecommendationRepositoryCustom {

}