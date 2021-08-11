package com.team.delightserver.service;

import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.food.FoodRepository;
import com.team.delightserver.web.domain.recommendation.Recommendation;
import com.team.delightserver.web.domain.recommendation.RecommendationRepository;
import com.team.delightserver.web.dto.request.SelectedFoodRequest;
import com.team.delightserver.web.dto.response.MachineLearningResultResponse;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import static com.team.delightserver.web.dto.response.RecommendedFoodResponse.recommendedData;

/**
 * @CreateBy:Min
 * @Date: 2021/07/27
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiMLRecommendationService {

    private static final String ML_SEVER_URI = "http://3.35.134.47:5000";
    private static final String ML_SEVER_PATH = "/api/ml-servers";
    private final RecommendationRepository recommendationRepository;
    private final FoodRepository foodRepository;

    /**
     * 머신러닝 결과를 받아 옵니다.
     */
    @Transactional
    public RecommendedFoodResponse getMlResults(SelectedFoodRequest selectedFoodRequestDto) {

        URI uri = UriComponentsBuilder
                .fromUriString(ML_SEVER_URI)
                .path(ML_SEVER_PATH)
                .encode()
                .build()
                .toUri();

        log.info("URI : {}", uri);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<MachineLearningResultResponse> responseEntity = restTemplate.postForEntity(
                uri,
                selectedFoodRequestDto,
                MachineLearningResultResponse.class
        );

        MachineLearningResultResponse machineLearningResultResponse = Objects.requireNonNull(responseEntity.getBody());
        saveRecommendations(machineLearningResultResponse);

        List<recommendedData> responseBody = getRecommendedData(machineLearningResultResponse);

        log.info("StatusCode : {}", responseEntity.getStatusCode());
        log.info("Headers info : {}", responseEntity.getHeaders());
        log.info("Response Body : {}", responseEntity.getBody());


        return RecommendedFoodResponse.of(responseBody);
    }

    /**
     * 여기서부터는 Extract Method 입니다.
     */
    private List<recommendedData> getRecommendedData(MachineLearningResultResponse
                                                             machineLearningResultResponse) {

        List<String> foods = machineLearningResultResponse.getFoods();
        List<Integer> scores = machineLearningResultResponse.getScores();
        int foodCount = machineLearningResultResponse.getFoods().size();

        List<recommendedData> responseBody = new ArrayList<>();

        IntStream.range(0, foodCount).forEach(result -> {

            String foodName = foods.get(result);
            int score = scores.get(result);

            Optional<Food> foodAttribute = foodRepository.findByName(foodName);
            Food food = foodAttribute.orElseThrow(()
                    -> new IllegalArgumentException("해당 음식이 없습니다."));
            String imgUrl = food.getImgUrl();

            responseBody.add(recommendedData.of(foodName, score, imgUrl));
        });

        return responseBody;
    }

    private void saveRecommendations(MachineLearningResultResponse machineLearningResultResponse) {

        List<String> recommendations = machineLearningResultResponse.getFoods();
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