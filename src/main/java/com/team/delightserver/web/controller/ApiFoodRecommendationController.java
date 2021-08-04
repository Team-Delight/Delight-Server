package com.team.delightserver.web.controller;

import com.team.delightserver.service.FoodRecommendationService;
import com.team.delightserver.web.dto.response.RankRecommendationsResponseDto;
import com.team.delightserver.web.dto.response.TopTenFoodCategoryResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @CreateBy:Min
 * @Date: 2021/08/04
 */

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/rank/recommendations")
@RestController
public class ApiFoodRecommendationController {

    private final FoodRecommendationService foodRecommendationService;

    @GetMapping("")
    public ResponseEntity<List<RankRecommendationsResponseDto>> findRankedFoods() {
        return ResponseEntity.ok()
                .body(foodRecommendationService.findRankedFoods());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<TopTenFoodCategoryResponseDto>> findFoodsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok()
                .body(foodRecommendationService.findTopTenFoodsByCategory(categoryId));
    }
}
