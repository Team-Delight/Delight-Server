package com.team.delightserver.web.domain.foodtag;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.delightserver.web.domain.tag.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.team.delightserver.web.domain.food.QFood.food;
import static com.team.delightserver.web.domain.foodtag.QFoodTag.foodTag;
import static com.team.delightserver.web.domain.tag.QTag.tag;

/**
 * @CreateBy:Min
 * @Date: 2021/08/13
 */

@RequiredArgsConstructor
public class FoodTagRepositoryImpl
        implements FoodTagRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Tag> findAllTagsByFoodName(String foodName) {
        return queryFactory.selectDistinct(tag)
                .from(foodTag)
                .innerJoin(foodTag.tag, tag)
                .innerJoin(foodTag.food, food)
                .where(foodTag.food.name.eq(foodName))
                .fetch();
    }
}
