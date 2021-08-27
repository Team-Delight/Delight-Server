package com.team.delightserver.service;

import com.team.delightserver.security.oauth2.OAuth2UserProvider;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.food.FoodRepository;
import com.team.delightserver.web.domain.foodtag.FoodTagRepository;
import com.team.delightserver.web.domain.recommendation.Recommendation;
import com.team.delightserver.web.domain.recommendation.RecommendationRepository;
import com.team.delightserver.web.domain.tag.Tag;
import com.team.delightserver.web.dto.request.SelectedFoodRequest;
import com.team.delightserver.web.dto.response.MachineLearningResultResponse;
import com.team.delightserver.web.dto.response.RecommendedFoodResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.team.delightserver.web.dto.response.RecommendedFoodResponse.recommendedData;

/**
 * @CreateBy: Min, Doe
 * @CreateDate: 2021/07/27
 * @ModifiedDate: 2021/08/13, 2021/08/27
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiMLRecommendationService {

    @Value("${spring.ml-server.url}")
    private String ML_SEVER_URL;
    private static final String ML_SEVER_PATH = "/api/ml-servers";
    private static final int ML_MAX_FEED_SIZE = 10;
    private final RecommendationRepository recommendationRepository;
    private final FoodRepository foodRepository;
    private final FoodTagRepository foodTagRepository;

    /**
     * 머신러닝 결과를 받아 옵니다.
     */
    @Transactional
    public RecommendedFoodResponse getMlResults(SelectedFoodRequest selectedFoodRequestDto) {

        URI uri = UriComponentsBuilder
                .fromUriString(ML_SEVER_URL)
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
        List<String> foods = machineLearningResultResponse.getFoods();
        List<Double> scores = machineLearningResultResponse.getScores();

        saveRecommendations(foods);

        List<recommendedData> responseBody = getRecommendedData(foods, scores);

        log.info("StatusCode : {}", responseEntity.getStatusCode());
        log.info("Headers info : {}", responseEntity.getHeaders());
        log.info("Response Body : {}", responseEntity.getBody());


        return RecommendedFoodResponse.of(responseBody);
    }

    /**
     * 지난주의 마이픽에 따른 ml 결과를 가져옵니다.
     */
    @Transactional
    public RecommendedFoodResponse getMlResultsFromMypick(OAuth2UserProvider user){
        List<Food> foods = foodRepository.findAllByUserMypickWithinWeek(user.toUser(), PageRequest.of(0, ML_MAX_FEED_SIZE));
        if (foods.size() > 0){
            List<String> foodNames = foods.stream().map(Food::getName).collect(Collectors.toList());
            return getMlResults(new SelectedFoodRequest(foodNames));
        }
        return new RecommendedFoodResponse(null);
    }

    /**
     * 여기서부터는 Extract Method 입니다.
     */
    private List<recommendedData> getRecommendedData(List<String> foods,
                                                     List<Double> scores) {
        int foodCount = foods.size();

        List<recommendedData> responseBody = new ArrayList<>();

        IntStream.range(0, foodCount).forEach(result -> {
            String foodName = foods.get(result);
            Double score = scores.get(result);

            Optional<Food> foodAttribute = foodRepository.findByName(foodName);
            Food food = foodAttribute.orElseThrow(()
                    -> new IllegalArgumentException("해당 음식이 없습니다."));
            String imgUrl = food.getImgUrl();
            List<Tag> tags = foodTagRepository.findAllTagsByFoodName(foodName);
            responseBody.add(recommendedData.of(foodName, score, imgUrl, tags));
        });

        return responseBody;
    }

    private void saveRecommendations(List<String> foods) {
        foods.stream()
                .map(foodRepository::findByName)
                .map(foodAttribute -> foodAttribute.orElseThrow(()
                -> new IllegalArgumentException("해당 음식이 없습니다.")))
                .map(Recommendation::of).forEach(recommendationRepository::save);
    }
}