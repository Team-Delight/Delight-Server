package com.team.delightserver.web.domain.foodrecommendation;

import com.team.delightserver.web.dto.response.TopTenFoodCategoryResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRecommendationRepository extends JpaRepository<FoodRecommendation, Long> {

    @Query("select f.food.category.id, f.food.imgUrl, f.food.name,f.recommendation.createdAt " +
            "from FoodRecommendation f " +
            "join fetch f.food " +
            "join fetch f.recommendation " +
            "where f.food.category.id = :categoryId " +
            "order by f.recommendation.count desc ")
    List<TopTenFoodCategoryResponseDto> TopTenFoodPerCategory(Long categoryId, Pageable pageable);
}