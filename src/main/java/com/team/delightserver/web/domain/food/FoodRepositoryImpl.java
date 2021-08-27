package com.team.delightserver.web.domain.food;

import com.querydsl.core.group.Group;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.delightserver.util.CustomListUtil;
import com.team.delightserver.web.domain.user.User;
import com.team.delightserver.web.dto.response.TagRelatedFoodsResponse;
import com.team.delightserver.web.dto.response.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import static com.querydsl.core.group.GroupBy.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.team.delightserver.web.domain.food.QFood.food;
import static com.team.delightserver.web.domain.foodtag.QFoodTag.foodTag;
import static com.team.delightserver.web.domain.mypick.QMypick.mypick;

/**
 * @Created by Doe
 * @Date: 2021/08/10
 * @ModifiedDate : 2021/08/20
 */

@RequiredArgsConstructor
public class FoodRepositoryImpl implements FoodRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TagRelatedFoodsResponse> findAllByTagIds(List<Long> tagIds, Pageable pageable) {

        Function<Map<Long, Group>, List<TagRelatedFoodsResponse>> queryMapToResponseList
                = queryMap -> queryMap.values().stream().map(
                group -> new TagRelatedFoodsResponse(
                group.getOne(food.name),
                group.getOne(food.imgUrl),
                group.getList(foodTag.tag)
                .stream().map(TagResponse::of).collect(Collectors.toSet())))
                .collect(Collectors.toList());

        Supplier<JPQLQuery<Long>> foodIdsIncludingEveryTagIdsQuery
                = () -> JPAExpressions
                .select(food.id)
                .from(food)
                .leftJoin(foodTag).on(food.id.eq(foodTag.food.id))
                .where(foodTag.tag.id.in(tagIds))
                .groupBy(food.id)
                .having(food.id.count().eq(Long.valueOf(tagIds.size())));

        JPAQuery<?> query = queryFactory.from(food);

        if (!tagIds.isEmpty()) {
            query.where(food.id.in(foodIdsIncludingEveryTagIdsQuery.get()));
        }

        Map<Long, Group> queryMap = query
                .from(food)
                .innerJoin(foodTag).on(food.id.eq(foodTag.food.id))
                .transform(groupBy(food.id).as(food.name, food.imgUrl, list(foodTag.tag)));

        List<TagRelatedFoodsResponse> responseList = queryMapToResponseList.apply(queryMap);

        return CustomListUtil.applyPageableToList(responseList, pageable);
    }

    @Override
    public List<Food> findAllByUserMypickWithinWeek(User currentUser, Pageable pageable) {
        return queryFactory
                .select(food)
                .from(mypick)
                .where(mypick.user.eq(currentUser))
                .where(mypick.createdAt.between(
                        LocalDateTime.now().toLocalDate().atStartOfDay().minusDays(7),
                        LocalDateTime.now()))
                .innerJoin(mypick.food,food)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
