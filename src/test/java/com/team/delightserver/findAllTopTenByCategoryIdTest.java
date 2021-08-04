package com.team.delightserver;

import com.team.delightserver.web.domain.category.Category;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.foodrecommendation.FoodRecommendation;
import com.team.delightserver.web.domain.foodrecommendation.FoodRecommendationRepository;
import com.team.delightserver.web.domain.recommendation.Recommendation;
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
        List<FoodRecommendation> result = foodRecommendationRepository.findAllTopTenByCategoryId();

        //then
        System.out.println("foodRecommendation = " + foodRecommendation.getRecommendation().getCount());
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getFood().getName(), is("닭볶음탕"));
        assertThat(result.get(0).getRecommendation().getCount(), is(10));
    }
}
