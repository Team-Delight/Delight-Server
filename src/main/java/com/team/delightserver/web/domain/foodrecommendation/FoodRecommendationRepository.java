package com.team.delightserver.web.domain.foodrecommendation;

import com.team.delightserver.web.dto.response.TopTenFoodCategoryResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRecommendationRepository extends JpaRepository<FoodRecommendation, Long> {
}