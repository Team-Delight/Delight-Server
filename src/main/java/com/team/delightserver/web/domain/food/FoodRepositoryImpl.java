package com.team.delightserver.web.domain.food;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.delightserver.util.CustomListUtil;
import com.team.delightserver.web.dto.request.FindFoodsByTagsRequest;
import com.team.delightserver.web.dto.response.TagRelatedFoodsResponse;
import com.team.delightserver.web.dto.response.TagResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

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
 * @ModifiedDate : 2021/08/17
 */

@RequiredArgsConstructor
public class FoodRepositoryImpl implements FoodRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TagRelatedFoodsResponse> findAllByTagIds(List<Long> tagIds, Pageable pageable) {

        JPAQuery<FindAllByTagQueryResult> query = queryFactory
                .select(Projections.constructor(FindAllByTagQueryResult.class,
                        food.id,
                        food.name,
                        food.imgUrl,
                        foodTag.tag.name,
                        foodTag.tag.id
                ))
                .from(food);

        if (!tagIds.isEmpty()) {
            BooleanExpression allInTagIds = tag.id.eq(tagIds.get(0));
            for (Long tagId : tagIds.subList(1, tagIds.size())) {
                allInTagIds = allInTagIds.or(tag.id.eq(tagId));
            }

            JPQLQuery<Long> jpaQuery = JPAExpressions
                    .select(foodTag.food.id)
                    .from(tag)
                    .where(allInTagIds)
                    .innerJoin(foodTag).on(tag.id.eq(foodTag.tag.id));

            query
                    .where(food.id.in(jpaQuery));
        }
        query
                .innerJoin(foodTag).on(food.id.eq(foodTag.food.id));

        List<TagRelatedFoodsResponse> overlapRemovedTags = removeOverlapTags(query.fetch());

        return CustomListUtil.applyPageableToList(overlapRemovedTags, pageable);
    }

    private List<TagRelatedFoodsResponse> removeOverlapTags(List<FindAllByTagQueryResult> query) {
        Map<Long, TagRelatedFoodsResponse> noOverlapMap = new HashMap<>();

        query.forEach(food -> {
            Long foodId = food.getId(), tagId = food.getTagId();
            String name = food.getName(), imgUrl = food.getImgUrl(), tagName = food.getTag();
            if (!noOverlapMap.containsKey(foodId)) {
                noOverlapMap.put(foodId, new TagRelatedFoodsResponse(name, imgUrl));
            }
            TagResponse tag = new TagResponse(tagId, tagName);
            noOverlapMap.get(foodId).getTags().add(tag);
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
        private final Long tagId;

        public FindAllByTagQueryResult(Long id, String name, String imgUrl, String tag, Long tagId) {
            this.id = id;
            this.name = name;
            this.imgUrl = imgUrl;
            this.tag = tag;
            this.tagId = tagId;
        }
    }
}
