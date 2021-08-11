package com.team.delightserver.web.domain.foodtag;

import com.team.delightserver.web.domain.BaseTimeEntity;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.tag.Tag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

/**
 * @CreateBy: Doe
 * @Date: 2021/08/10
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class FoodTag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @JoinColumn(name = "food_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;

    @NonNull
    @JoinColumn(name = "tag_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;
}
