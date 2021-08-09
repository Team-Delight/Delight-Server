package com.team.delightserver.web.controller;

import com.team.delightserver.service.ApiFoodRecommendationService;
import com.team.delightserver.web.dto.response.RecommendationRankResponse;
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
@RequestMapping("/api/recommendations")
@RestController
public class ApiFoodRecommendationController {

    private final ApiFoodRecommendationService foodRecommendationService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<RecommendationRankResponse>> findFoodsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok()
                .body(foodRecommendationService.findTopTenFoodsByCategory(categoryId));
    }
}
