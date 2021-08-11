package com.team.delightserver.web.controller;

import com.team.delightserver.service.ApiMLRecommendationService;
import com.team.delightserver.web.dto.request.SelectedFoodRequest;
import com.team.delightserver.web.dto.response.RecommendedFoodResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CreateBy:Min
 * @Date: 2021/07/27
 */

@Slf4j
@RequestMapping("/api/ml-recommendations")
@RequiredArgsConstructor
@RestController
public class ApiMLRecommendationController {

    private final ApiMLRecommendationService apiRestTemplateService;

    @PostMapping("")
    public ResponseEntity<RecommendedFoodResponse> findMlResults(@RequestBody SelectedFoodRequest selectedFoodRequestDto) {
        return ResponseEntity.ok().body(apiRestTemplateService.getMlResults(selectedFoodRequestDto));
    }
}