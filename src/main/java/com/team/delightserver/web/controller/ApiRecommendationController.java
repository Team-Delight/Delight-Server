package com.team.delightserver.web.controller;

import com.team.delightserver.service.ApiRecommendationService;
import com.team.delightserver.web.dto.response.RecommendationRankResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CreateBy:Min
 * @Date: 2021/08/04
 */

@Slf4j
@RequiredArgsConstructor
@RequestMapping ("/api/recommendations")
@RestController
public class ApiRecommendationController {

    private final ApiRecommendationService recommendationService;

    /**
     * 카테고리별 추천 랭킹 조회
     */
    @GetMapping ("/{categoryId}")
    public ResponseEntity<List<RecommendationRankResponse>> findFoodsByCategory ( @PathVariable Long categoryId ) {
        return ResponseEntity.ok().body(recommendationService.findTopFiveFoodsByCategory(categoryId));
    }
}
