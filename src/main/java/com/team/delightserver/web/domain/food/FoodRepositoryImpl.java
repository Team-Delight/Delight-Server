package com.team.delightserver.web.domain.food;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
 * @ModifiedDate : 2021/08/11
 */

@RequiredArgsConstructor
public class FoodRepositoryImpl implements FoodRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TagRelatedFoodsResponse> findAllByTagId(Long categoryId, Pageable pageable) {

        JPAQuery<FindAllByTagQueryResult> query = queryFactory
                .select(Projections.constructor(FindAllByTagQueryResult.class,
                        food.id,
                        food.name,
                        food.imgUrl,
                        tag.name,
                        tag.id
                ))
                .from(food);

        if (categoryId != 0) {
            query
                 .where(food.id.in(JPAExpressions
                         .select(foodTag.food.id)
                         .from(tag)
                         .where(tag.id.eq(categoryId))
                         .innerJoin(foodTag).on(tag.id.eq(foodTag.tag.id))
                 ));
        }

        query
                .innerJoin(foodTag).on(food.id.eq(foodTag.food.id))
                .innerJoin(tag).on(foodTag.tag.id.eq(tag.id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return removeOverlapTags(query.fetch());
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