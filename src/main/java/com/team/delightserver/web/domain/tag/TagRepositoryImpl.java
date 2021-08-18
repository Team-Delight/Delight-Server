package com.team.delightserver.web.domain.tag;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.delightserver.util.enumclass.TagType;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.team.delightserver.web.domain.food.QFood.food;
import static com.team.delightserver.web.domain.foodtag.QFoodTag.foodTag;
import static com.team.delightserver.web.domain.mypick.QMypick.mypick;
import static com.team.delightserver.web.domain.tag.QTag.tag;

/**
 * @CreateBy: Doe
 * @Date: 2021/08/18
 */

@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Tag> findMostFrequentTagByUserId(Long userId, TagType tagType) {

        List<Tag> fetch = queryFactory
                .select(Projections.constructor(Tag.class,
                        tag.id, tag.name, tag.type
                ))
                .from(mypick)
                .where(mypick.user.id.eq(userId))
                .where(tag.type.eq(tagType))
                .innerJoin(food).on(mypick.food.id.eq(food.id))
                .innerJoin(foodTag).on(food.id.eq(foodTag.food.id))
                .innerJoin(tag).on(foodTag.tag.id.eq(tag.id))
                .groupBy(tag.id)
                .orderBy(tag.id.count().desc())
                .limit(1L)
                .fetch();

        return fetch.stream().findFirst();
    }
}