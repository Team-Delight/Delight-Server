package com.team.delightserver.web.domain.foodrecommendation;

import com.team.delightserver.web.domain.Timestamped;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.recommendation.Recommendation;
import lombok.AccessLevel;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;

    @JoinColumn(name = "recommendation_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Recommendation recommendation;
}
