package com.team.delightserver.web.domain.foodrecommendation;

import com.team.delightserver.web.domain.Timestamped;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.recommendation.Recommendation;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @CreateBy:Min
 * @Date: 2021/08/02
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class FoodRecommendation extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "food_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Food food;

    @JoinColumn(name = "recommendation_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Recommendation recommendation;

    @Builder
    public FoodRecommendation(Long id, Food food, Recommendation recommendation) {
        this.id = id;
        this.food = food;
        this.recommendation = recommendation;
    }

    public static FoodRecommendation of(Food food, Recommendation recommendation) {
        return FoodRecommendation.builder()
                .food(food)
                .recommendation(recommendation)
                .build();
    }
}
