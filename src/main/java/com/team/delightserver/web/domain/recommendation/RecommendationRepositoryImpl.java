package com.team.delightserver.web.domain.recommendation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.delightserver.web.dto.response.RecommendationRankResponse;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.querydsl.core.types.ExpressionUtils.count;
import static com.team.delightserver.web.domain.category.QCategory.category;
import static com.team.delightserver.web.domain.food.QFood.food;
import static com.team.delightserver.web.domain.recommendation.QRecommendation.recommendation;

/**
 * @CreateBy:Min
 * @Date: 2021/08/18
 */

@RequiredArgsConstructor
public class RecommendationRepositoryImpl implements RecommendationRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private static final int VIEW_COUNTS = 5;


    @Override
    public List<RecommendationRankResponse> findAllTopTenByCategoryId(Long id) {

        JPAQuery<RecommendationRankResponse> query = queryFactory
                .selectDistinct(Projections.constructor(RecommendationRankResponse.class,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(count(recommendation))
                                        .from(recommendation)
                                        .where(recommendation.food.id.eq(food.id)),
                                "recommendedCnt"),
                        food.name,
                        food.imgUrl,
                        food.category.id))
                .from(recommendation)
                .innerJoin(recommendation.food, food)
                .innerJoin(food.category, category);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        isValidTime(booleanBuilder);
        whetherCategoryId(id, booleanBuilder);

        return query.where(booleanBuilder)
                .orderBy(new OrderSpecifier<>(Order.DESC,
                        Expressions.numberPath(Long.class, "recommendedCnt")))
                .limit(VIEW_COUNTS)
                .fetch();
    }

    private void isValidTime(BooleanBuilder booleanBuilder) {

        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime startAmToday = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endAmToday = LocalDateTime.now().toLocalDate().atStartOfDay().plusHours(12);
        LocalDateTime startYesterday = startAmToday.minusDays(1);


        if (nowTime.isAfter(startAmToday) && nowTime.isBefore(endAmToday)) {
            booleanBuilder.and(recommendation.createdAt
                    .between(startYesterday, startAmToday));
        }
        if (nowTime.isAfter(endAmToday)) {
            booleanBuilder.and(recommendation.createdAt
                    .goe(startAmToday));
        }
    }

    private void whetherCategoryId(Long id, BooleanBuilder booleanBuilder) {
        if (id != null && id != 0) {
            booleanBuilder.and(categoryIdEq(id));
        }
    }

    private BooleanExpression categoryIdEq(Long id) {
        return id != null ? food.category.id.eq(id) : null;
    }
}
