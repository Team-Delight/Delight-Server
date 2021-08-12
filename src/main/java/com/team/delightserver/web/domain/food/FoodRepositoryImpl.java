package com.team.delightserver.web.domain.food;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.delightserver.web.dto.response.TagRelatedFoodsResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.team.delightserver.web.domain.food.QFood.food;
import static com.team.delightserver.web.domain.foodtag.QFoodTag.foodTag;
import static com.team.delightserver.web.domain.tag.QTag.tag;

/**
 * @Created by Doe
 * @Date: 2021/08/10
 */

@RequiredArgsConstructor
public class FoodRepositoryImpl implements FoodRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TagRelatedFoodsResponse> findAllByTagId(Long categoryId) {

        List<FindAllByTagQueryResult> query = queryFactory
                .select(Projections.constructor(FindAllByTagQueryResult.class,
                        food.id,
                        food.name,
                        food.imgUrl,
                        tag.name
                ))
                .from(food)
                .where(food.id.in(JPAExpressions
                        .select(foodTag.food.id)
                        .from(tag)
                        .where(tag.id.eq(categoryId))
                        .innerJoin(foodTag).on(tag.id.eq(foodTag.tag.id))
                ))
                .innerJoin(foodTag).on(food.id.eq(foodTag.food.id))
                .innerJoin(tag).on(foodTag.tag.id.eq(tag.id))
                .fetch();

        return removeOverlapTags(query);
    }

    private List<TagRelatedFoodsResponse> removeOverlapTags(List<FindAllByTagQueryResult> query) {
        Map<Long, TagRelatedFoodsResponse> noOverlapMap = new HashMap<>();

        query.forEach(food -> {
            Long id = food.getId();
            String name = food.getName(), imgUrl = food.getImgUrl(), tag = food.getTag();
            if (!noOverlapMap.containsKey(id)) {
                noOverlapMap.put(id, new TagRelatedFoodsResponse(name, imgUrl));
            }
            noOverlapMap.get(id).getTags().add(tag);
        });

        return new ArrayList<>(noOverlapMap.values());
    }

    /**
     * 쿼리의 tag 를 담는 food 결과가 중복되어 나오기에 중복 정제과정 중간 sql result 를 받는 Type 입니다.
     */
    @Getter
    public static class FindAllByTagQueryResult {
        private final Long id;
        private final String name;
        private final String imgUrl;
        private final String tag;

        public FindAllByTagQueryResult(Long id, String name, String imgUrl, String tag) {
            this.id = id;
            this.name = name;
            this.imgUrl = imgUrl;
            this.tag = tag;
        }
    }
}
