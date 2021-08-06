package com.team.delightserver.service;

import com.team.delightserver.web.domain.recommendation.Recommendation;
import com.team.delightserver.web.domain.recommendation.RecommendationRepository;
import com.team.delightserver.web.dto.request.SelectedFoodRequestDto;
import com.team.delightserver.web.dto.response.RecommendedFoodResponse;
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
import java.util.Objects;

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
    public RecommendedFoodResponse getMlResults(SelectedFoodRequestDto selectedFoodRequestDto) {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/Ml-servers")
                .encode()
                .build()
                .toUri();

        log.info("URI : {}", uri);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<RecommendedFoodResponse> responseEntity = restTemplate.postForEntity(
                uri,
                selectedFoodRequestDto,
                RecommendedFoodResponse.class
        );

        RecommendedFoodResponse recommendedFoodResponse = Objects.requireNonNull(responseEntity.getBody());
        saveRecommendations(recommendedFoodResponse);

        log.info("StatusCode : {}", responseEntity.getStatusCode());
        log.info("Headers info : {}", responseEntity.getHeaders());
        log.info("Response Body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    private void saveRecommendations(RecommendedFoodResponse recommendedFoodResponse) {

        List<String> recommendations = recommendedFoodResponse.getFoods();
        LocalDate today = LocalDate.now();

        recommendations.forEach(recommendedFood -> TwoTypeOfSave(today, recommendedFood));
    }

    private void TwoTypeOfSave(LocalDate today, String recommendedFood) {
        ifExistAndCreatedAtToday(today, recommendedFood);
        ifNotExist(recommendedFood);
    }

    private void ifNotExist(String recommendedFood) {
        if (!ExistRecommendation(recommendedFood)) {
            int count = 1;
            Recommendation recommendation = Recommendation.of(recommendedFood, count);
            recommendationRepository.save(recommendation);
        }
    }

    private void ifExistAndCreatedAtToday(LocalDate today, String recommendedFood) {
        if (ExistRecommendation(recommendedFood)) {
            Recommendation recommendation = recommendationRepository.findByName(recommendedFood);

            if (isTodayRecommendation(today, recommendation)) {
                recommendation.addCount(recommendation.getCount() + 1);
                recommendationRepository.save(recommendation);
            }
        }
    }

    private boolean isTodayRecommendation(LocalDate today, Recommendation recommendation) {
        return recommendation.getCreatedAt().toLocalDate().equals(today);
    }

    private boolean ExistRecommendation(String recommendedFood) {
        return recommendationRepository.findByName(recommendedFood) != null;
    }
}