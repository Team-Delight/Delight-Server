package com.team.delightserver.web.domain.foodrecommendation;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.delightserver.web.domain.category.QCategory;
import com.team.delightserver.web.dto.response.TopTenFoodCategoryResponseDto;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.team.delightserver.web.domain.food.QFood.food;
import static com.team.delightserver.web.domain.foodrecommendation.QFoodRecommendation.foodRecommendation;
import static com.team.delightserver.web.domain.recommendation.QRecommendation.recommendation;

@RequiredArgsConstructor
public class FoodRecommendationRepositoryImpl
        implements FoodRecommendationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TopTenFoodCategoryResponseDto> findAllTopTenByCategoryId(Long id) {
        QCategory category = QCategory.category;
        LocalDateTime today = LocalDateTime.now().toLocalDate().atStartOfDay();

        return queryFactory
                .select(foodRecommendation.recommendation.count,
                        foodRecommendation.food.name,
                        foodRecommendation.food.imgUrl,
                        foodRecommendation.food.category.id)
                .from(foodRecommendation)
                .innerJoin(foodRecommendation.recommendation, recommendation)
                .innerJoin(foodRecommendation.food, food)
                .innerJoin(food.category, category)
                .where(foodRecommendation.recommendation.createdAt.gt(today))
                .where(foodRecommendation.food.category.id.eq(id))
                .orderBy(foodRecommendation.recommendation.count.desc())
                .fetch()
                .stream()
                .map(tuple -> new TopTenFoodCategoryResponseDto())
                .collect(Collectors.toList());
    }
}