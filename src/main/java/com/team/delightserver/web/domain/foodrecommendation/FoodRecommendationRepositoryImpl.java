package com.team.delightserver.web.domain.foodrecommendation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.delightserver.web.dto.response.QRecommendationRankResponse;
import com.team.delightserver.web.dto.response.RecommendationRankResponse;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    public List<RecommendationRankResponse> findAllTopTenByCategoryId(Long id) {

        JPAQuery<RecommendationRankResponse> query = queryFactory
                .select(new QRecommendationRankResponse(
                        foodRecommendation.recommendation.count,
                        foodRecommendation.food.name,
                        foodRecommendation.food.imgUrl,
                        foodRecommendation.food.category.id))
                .from(foodRecommendation)
                .innerJoin(foodRecommendation.recommendation, recommendation)
                .innerJoin(foodRecommendation.food, food)
                .innerJoin(food.category, category);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        /*
          현재시간이 오전인지 오후인지 판별
          오전 : 전날 추천받은 게시물 , 오후 : 당일 추천받은 게시물
         */
        whetherAmPm(booleanBuilder);

        /*
         CategoryId = 0 이면 전체 추천랭킹 게시물 조회
         CategoryId != 0 이면 해당 카테고리별 게시물 조회
         */
        whetherCategoryId(id, booleanBuilder);

        return query.where(booleanBuilder)
                .orderBy(countDesc())
                .limit(10)
                .fetch();
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
        if (id != null && id != 0) {
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