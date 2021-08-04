package com.team.delightserver.web.domain.foodrecommendation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.delightserver.web.dto.response.RankRecommendationsResponseDto;
import com.team.delightserver.web.dto.response.TopTenFoodCategoryResponseDto;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.team.delightserver.web.domain.category.QCategory.category;
import static com.team.delightserver.web.domain.food.QFood.food;
import static com.team.delightserver.web.domain.foodrecommendation.QFoodRecommendation.foodRecommendation;
import static com.team.delightserver.web.domain.recommendation.QRecommendation.recommendation;

/**
 * @CreateBy:Min
 * @Date: 2021/08/05
 */

@RequiredArgsConstructor
public class FoodRecommendationRepositoryImpl
        implements FoodRecommendationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<RankRecommendationsResponseDto> findTopTenFoods() {
        return queryFactory
                .select(foodRecommendation.recommendation.count,
                        foodRecommendation.food.name,
                        foodRecommendation.food.imgUrl,
                        foodRecommendation.food.category.id)
                .from(foodRecommendation)
                .innerJoin(foodRecommendation.recommendation, recommendation)
                .innerJoin(foodRecommendation.food, food)
                .innerJoin(food.category, category)
                .orderBy(countDesc())
                .fetch()
                .stream()
                .map(tuple -> new RankRecommendationsResponseDto())
                .collect(Collectors.toList());
    }

    @Override
    public List<TopTenFoodCategoryResponseDto> findAllTopTenByCategoryId(Long id) {

        JPAQuery<Tuple> query = queryFactory
                .select(foodRecommendation.recommendation.count,
                        foodRecommendation.food.name,
                        foodRecommendation.food.imgUrl,
                        foodRecommendation.food.category.id)
                .from(foodRecommendation)
                .innerJoin(foodRecommendation.recommendation, recommendation)
                .innerJoin(foodRecommendation.food, food)
                .innerJoin(food.category, category);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        whetherAmPm(booleanBuilder);
        whetherCategoryId(id, booleanBuilder);

        return query.where(booleanBuilder)
                .orderBy(countDesc())
                .fetch()
                .stream()
                .map(tuple -> new TopTenFoodCategoryResponseDto())
                .collect(Collectors.toList());
    }

    private void whetherAmPm(BooleanBuilder booleanBuilder) {

        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime startAmToday = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endAmToday = LocalDateTime.now().toLocalDate().atStartOfDay().plusHours(12);
        LocalDateTime endPmToday = LocalDateTime.now().toLocalDate().atStartOfDay().plusDays(1);

        if (nowTime.isBefore(startAmToday) && nowTime.isAfter(endAmToday)) {
            booleanBuilder.and(foodRecommendation.recommendation.createdAt.goe(startAmToday)
                    .and(foodRecommendation.recommendation.createdAt.lt(startAmToday)));
        }
        if (nowTime.isEqual(endAmToday) && nowTime.isBefore(endAmToday)) {
            booleanBuilder.and(foodRecommendation.recommendation.createdAt.goe(endAmToday)
                    .and(foodRecommendation.recommendation.createdAt.lt(endPmToday)));
        }
    }

    private void whetherCategoryId(Long id, BooleanBuilder booleanBuilder) {
        if (id != null) {
            booleanBuilder.and(categoryIdEq(id));
        }
    }

    private OrderSpecifier<Integer> countDesc() {
        return foodRecommendation.recommendation.count.desc();
    }

    private BooleanExpression categoryIdEq(Long id) {
        return id != null ? foodRecommendation.food.category.id.eq(id) : null;
    }
}