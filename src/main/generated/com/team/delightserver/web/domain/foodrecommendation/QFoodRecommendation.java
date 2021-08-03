package com.team.delightserver.web.domain.foodrecommendation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodRecommendation is a Querydsl query type for FoodRecommendation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFoodRecommendation extends EntityPathBase<FoodRecommendation> {

    private static final long serialVersionUID = 1267881080L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodRecommendation foodRecommendation = new QFoodRecommendation("foodRecommendation");

    public final com.team.delightserver.web.domain.QTimestamped _super = new com.team.delightserver.web.domain.QTimestamped(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.team.delightserver.web.domain.food.QFood food;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.team.delightserver.web.domain.recommendation.QRecommendation recommendation;

    public QFoodRecommendation(String variable) {
        this(FoodRecommendation.class, forVariable(variable), INITS);
    }

    public QFoodRecommendation(Path<? extends FoodRecommendation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodRecommendation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodRecommendation(PathMetadata metadata, PathInits inits) {
        this(FoodRecommendation.class, metadata, inits);
    }

    public QFoodRecommendation(Class<? extends FoodRecommendation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.food = inits.isInitialized("food") ? new com.team.delightserver.web.domain.food.QFood(forProperty("food"), inits.get("food")) : null;
        this.recommendation = inits.isInitialized("recommendation") ? new com.team.delightserver.web.domain.recommendation.QRecommendation(forProperty("recommendation")) : null;
    }

}

