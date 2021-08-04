package com.team.delightserver.web.domain.foodrecommendation;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.delightserver.web.domain.food.QFood;
import com.team.delightserver.web.domain.recommendation.QRecommendation;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.team.delightserver.web.domain.food.QFood.food;
import static com.team.delightserver.web.domain.foodrecommendation.QFoodRecommendation.foodRecommendation;
import static com.team.delightserver.web.domain.recommendation.QRecommendation.recommendation;

@RequiredArgsConstructor
public class FoodRecommendationRepositoryImpl
        implements FoodRecommendationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // 전체도 하나 만들기
    @Override
    public List<FoodRecommendation> findAllTopTenByCategoryId() {
        QFoodRecommendation foodRecommendation = QFoodRecommendation.foodRecommendation;
        QFood food = QFood.food;
        QRecommendation recommendation = QRecommendation.recommendation;
        LocalDateTime today = LocalDateTime.now();
        return queryFactory.select(foodRecommendation)
                .from(foodRecommendation)
                .innerJoin(foodRecommendation.food, food)
                .fetchJoin()
//                .where(foodRecommendation.recommendation.createdAt.lt())
//                .orderBy(foodRecommendation.recommendation.count.desc())
//                .limit(10)
                .fetch();
    }

//    @Override
//    public List<FoodRecommendation> findAllTopTenByCategoryId(Long id) {
//        return queryFactory.select(foodRecommendation.food.category.id,
//                foodRecommendation.food.name,
//                foodRecommendation.food.imgUrl,
//                foodRecommendation.recommendation.createdAt)
//                .from(foodRecommendation.food.category)
//                .join(foodRecommendation,
//                        foodRecommendation.food,
//                        foodRecommendation.food.category,
//                        foodRecommendation.recommendation)
//                .where(foodRecommendation.food.category.eq(id))
//                .orderBy(foodRecommendation.recommendation.count.desc())
//                .limit(10)
//                .fetch();
//    }
}