package com.team.delightserver.web.controller;

import com.team.delightserver.service.FoodRecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CreateBy:Min
 * @Date: 2021/08/04
 */

@Slf4j
@RequiredArgsConstructor
@RestController
public class ApiFoodRecommendationController {

    private final FoodRecommendationService foodRecommendationService;
}
