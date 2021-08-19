package com.team.delightserver.web.domain.mypick;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.delightserver.web.domain.foodtag.FoodTagRepository;
import com.team.delightserver.web.domain.tag.Tag;
import com.team.delightserver.web.dto.response.MypickResponse;
import com.team.delightserver.web.dto.response.MypickResponse.MypickWithoutTagsResponse;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.team.delightserver.web.domain.food.QFood.food;
import static com.team.delightserver.web.domain.mypick.QMypick.mypick;

/**
 * @CreateBy:Min
 * @Date: 2021/08/19
 */

@RequiredArgsConstructor
public class MypickRepositoryImpl implements MypickRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final FoodTagRepository foodTagRepository;

    @Override
    public List<MypickResponse> findMypickByUserId(Long userId) {

        List<MypickWithoutTagsResponse> mypickFoods = queryFactory
                .select(Projections.constructor(MypickWithoutTagsResponse.class,
                        ExpressionUtils.as(
                                new CaseBuilder()
                                        .when(mypick.createdAt.hour().between(4, 9))
                                        .then("아침")
                                        .when(mypick.createdAt.hour().between(10, 15))
                                        .then("점심")
                                        .when(mypick.createdAt.hour().between(16, 21))
                                        .then("저녁")
                                        .when(mypick.createdAt.hour().between(22, 23))
                                        .then("야식")
                                        .when(mypick.createdAt.hour().between(0, 3))
                                        .then("야식")
                                        .otherwise(""),
                                "timeType"),
                        food.name,
                        food.imgUrl,
                        mypick.createdAt))
                .from(mypick)
                .innerJoin(mypick.food, food)
                .where(mypick.user.id.eq(userId))
                .where(mypick.createdAt.between(
                        LocalDateTime.now().toLocalDate().atStartOfDay().minusDays(7)
                        , LocalDateTime.now()))
                .orderBy(mypick.createdAt.desc())
                .fetch();

        List<MypickResponse> mypickInfoIncludedTags = new ArrayList<>();
        mypickFoods.forEach(mypickFood -> {
            String foodName = mypickFood.getName();
            List<Tag> tagsByFoodName = foodTagRepository.findAllTagsByFoodName(foodName);
            mypickInfoIncludedTags.add(MypickResponse.of(tagsByFoodName, mypickFood));
        });

        return mypickInfoIncludedTags;
    }
}
