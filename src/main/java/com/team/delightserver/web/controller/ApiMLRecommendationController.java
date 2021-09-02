package com.team.delightserver.web.controller;

import com.team.delightserver.security.annotation.CurrentUser;
import com.team.delightserver.security.oauth2.OAuth2UserProvider;
import com.team.delightserver.service.ApiMLRecommendationService;
import com.team.delightserver.web.dto.request.SelectedFoodRequest;
import com.team.delightserver.web.dto.response.RecommendedFoodResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CreateBy: Min, Doe
 * @Date: 2021/07/27
 * @ModifiedDate : 2021/08/27
 */
@RequiredArgsConstructor
@RequestMapping("/api/ml-recommendations")
@RestController
public class ApiMLRecommendationController {

    private final ApiMLRecommendationService apiMLRecommendationService;

    @PostMapping("")
    public ResponseEntity<RecommendedFoodResponse> findMlResults(@RequestBody SelectedFoodRequest selectedFoodRequestDto) {
        return ResponseEntity.ok().body(apiMLRecommendationService.getMlResults(selectedFoodRequestDto));
    }

    @GetMapping("/mypicks")
    public ResponseEntity<RecommendedFoodResponse> findMlResultsFromMypick(@CurrentUser OAuth2UserProvider user) {
        return ResponseEntity.ok().body(apiMLRecommendationService.getMlResultsFromMypick(user));
    }
}