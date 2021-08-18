package com.team.delightserver.web.domain.recommendation;

import com.team.delightserver.web.domain.BaseTimeEntity;
import com.team.delightserver.web.domain.food.Food;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @CreateBy:Min
 * @Date: 2021/08/02
 * @ModifiedDate: 2021/08/18
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Recommendation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "food_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;

    @Builder
    public Recommendation(Long id, Food food) {
        this.id = id;
        this.food = food;
    }

    public static Recommendation of(Food food) {
        return Recommendation.builder()
                .food(food)
                .build();
    }
}
