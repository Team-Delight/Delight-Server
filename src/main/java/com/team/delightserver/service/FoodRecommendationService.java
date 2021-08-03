package com.team.delightserver.service;

import com.team.delightserver.web.domain.foodrecommendation.FoodRecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @CreateBy:Min
 * @Date: 2021/08/04
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class FoodRecommendationService {

    private final FoodRecommendationRepository foodRecommendationRepository;
}
