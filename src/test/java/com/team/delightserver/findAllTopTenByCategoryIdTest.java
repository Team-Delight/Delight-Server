package com.team.delightserver;

import com.team.delightserver.web.domain.category.Category;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.foodrecommendation.FoodRecommendation;
import com.team.delightserver.web.domain.foodrecommendation.FoodRecommendationRepository;
import com.team.delightserver.web.domain.recommendation.Recommendation;
import com.team.delightserver.web.dto.response.TopTenFoodCategoryResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class findAllTopTenByCategoryIdTest {

    @Autowired
    private FoodRecommendationRepository foodRecommendationRepository;

    @Test
    @Transactional
    public void queryDSL_쿼리문_확인() {
        //given
        Food food = Food.of("닭볶음탕",
                "http://",
                "맛있는 닭요리",
                Category.of("한식"));
        Recommendation recommendation = Recommendation.of("닭볶음탕", 10);
        FoodRecommendation foodRecommendation = FoodRecommendation.of(food, recommendation);

        foodRecommendationRepository.save(foodRecommendation);

        //when
        List<TopTenFoodCategoryResponseDto> tenFoodCategoryResponseDtos =
                foodRecommendationRepository.findAllTopTenByCategoryId(food.getCategory().getId());

        //then
        System.out.println("result imgUrl = " + tenFoodCategoryResponseDtos.get(0).getImgUrl());
        System.out.println("result name = " + tenFoodCategoryResponseDtos.get(0).getName());
        System.out.println("result id = " + tenFoodCategoryResponseDtos.get(0).getId());
        System.out.println("result count = " + tenFoodCategoryResponseDtos.get(0).getCount());

        assertThat(tenFoodCategoryResponseDtos.size(), is(1));
//        assertThat(tenFoodCategoryResponseDtos.get(0).getName(), is(null));
        assertThat(tenFoodCategoryResponseDtos.get(0).getCount(), is(0));
    }
}
