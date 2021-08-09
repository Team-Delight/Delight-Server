package com.team.delightserver.web.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.team.delightserver.service.ApiRestTemplateService;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.food.FoodRepository;
import com.team.delightserver.web.dto.response.RandomFoodsResponse;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Created by Bloo
 * @Date: 2021/08/06
 */
@Disabled
@SpringBootTest
class ApiRestTemplateServiceTests {

    @Autowired
    private ApiRestTemplateService apiRestTemplateService;

    @Autowired
    private FoodRepository foodRepository;

    @Disabled
    @DisplayName ("음식 20개 랜덤 조회를 테스트")
    @Test
    void findAllRandomFoods () {
        // given
        // when
        List<Food> foods = foodRepository.findAllRandom();
        assertThat(foods.size()).isEqualTo(20);

        List<RandomFoodsResponse> foodsRandom = apiRestTemplateService.findFoodsRandom();
        foodsRandom.forEach(System.out::println);
    }
}