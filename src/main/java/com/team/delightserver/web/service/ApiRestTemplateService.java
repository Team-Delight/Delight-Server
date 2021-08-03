package com.team.delightserver.web.service;

import com.team.delightserver.web.domain.food.Recommendation;
import com.team.delightserver.web.domain.food.RecommendationRepository;
import com.team.delightserver.web.dto.request.SelectedFoodRequestDto;
import com.team.delightserver.web.dto.response.RecommendedFoodResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

/**
 * @CreateBy:Min
 * @Date: 2021/07/27
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiRestTemplateService {

    private final RecommendationRepository recommendationRepository;

    @Transactional
    public RecommendedFoodResponseDto getMlResults(SelectedFoodRequestDto selectedFoodRequestDto) {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/Ml-servers")
                .encode()
                .build()
                .toUri();

        log.info("URI : {}", uri);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<RecommendedFoodResponseDto> responseEntity = restTemplate.postForEntity(
                uri,
                selectedFoodRequestDto,
                RecommendedFoodResponseDto.class
        );

        saveRecommendations(responseEntity.getBody());

        log.info("StatusCode : {}", responseEntity.getStatusCode());
        log.info("Headers info : {}", responseEntity.getHeaders());
        log.info("Response Body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    private void saveRecommendations(RecommendedFoodResponseDto recommendedFoodResponseDto) {

        List<String> recommendations = recommendedFoodResponseDto.getFoods();
        LocalDate today = LocalDate.now();

        recommendations.forEach(recommendedFood -> {
            TwoTypeOfSave(today, recommendedFood);
        });
    }

    private void TwoTypeOfSave(LocalDate today, String recommendedFood) {
        if (ExistRecommendation(recommendedFood)) {
            Recommendation recommendation = recommendationRepository.findByName(recommendedFood);

            if (isTodayRecomendation(today, recommendation)) {
                recommendation.addCount(recommendation.getCount() + 1);
                recommendationRepository.save(recommendation);
            }
        }
        if (!ExistRecommendation(recommendedFood)) {
            int count = 1;
            recommendationRepository.save(Recommendation.of(recommendedFood, count));
        }
    }

    private boolean isTodayRecomendation(LocalDate today, Recommendation recommendation) {
        return recommendation.getCreatedAt().toLocalDate().equals(today);
    }

    private boolean ExistRecommendation(String recommendedFood) {
        return recommendationRepository.findByName(recommendedFood) != null;
    }
}