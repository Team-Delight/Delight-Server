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

    @Column(nullable = false)
    private String name;

    @JoinColumn(name = "food_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;

    @Builder
    public Recommendation(Long id, String name, Food food) {
        this.id = id;
        this.name = name;
        this.food = food;
    }

    public static Recommendation of(String name, Food food) {
        return Recommendation.builder()
                .name(name)
                .food(food)
                .build();
    }
}
