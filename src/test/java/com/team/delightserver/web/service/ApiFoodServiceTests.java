package com.team.delightserver.web.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.team.delightserver.service.ApiFoodService;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.food.FoodRepository;
import com.team.delightserver.web.dto.response.SurveyFoodResponse;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Created by Bloo
 * @Date: 2021/08/06
 */

@SpringBootTest
class ApiFoodServiceTests {

    @Autowired
    private ApiFoodService apiFoodService;

    @Autowired
    private FoodRepository foodRepository;


    @DisplayName ("음식 20개 랜덤 조회를 테스트")
    @Test
    void findAllRandomFoods () {
        // given
        // when
        List<Food> foods = foodRepository.findAllRandom();

        List<SurveyFoodResponse> foodsRandom = apiFoodService.findRandomFoodsForSurvey(1L);
        foodsRandom.forEach(System.out::println);
    }
}