package com.team.delightserver.service;

import com.team.delightserver.util.redis.RedisSurveyFoodUtil;
import com.team.delightserver.web.domain.food.FoodRepository;
import com.team.delightserver.web.domain.food.RedisCacheFood;
import com.team.delightserver.web.dto.request.FindFoodsByTagsRequest;
import com.team.delightserver.web.dto.response.RandomFoodResponse;
import com.team.delightserver.web.dto.response.SurveyFoodResponse;
import com.team.delightserver.web.dto.response.TagRelatedFoodsResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @CreateBy: Min, Doe, BLoo
 * @Date: 2021/08/02, 2021/08/11
 * @ModifiedDate: 2021/08/17
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiFoodService {

    private final FoodRepository foodRepository;
    private final RedisSurveyFoodUtil redisSurveyFoodUtil;

    @Transactional(readOnly = true)
    public List<SurveyFoodResponse> findRandomFoodsForSurvey ( Long categoryId ) {
        List<RedisCacheFood> redisCacheFoods = redisSurveyFoodUtil.findRedisCacheFoodsByCategoryId(categoryId);
        return redisCacheFoods
            .stream()
            .map(SurveyFoodResponse::of)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TagRelatedFoodsResponse> findFoodsByTags(FindFoodsByTagsRequest findFoodsByTagsRequest, Pageable pageable) {
        return foodRepository.findAllByTagIds(findFoodsByTagsRequest.getTagIds(), pageable);
    }


    /**
     * 아래부터 프론트 개선 후 삭제될 로직 입니다.
     */
    @Transactional(readOnly = true)
    public List<RandomFoodResponse> findRandomFoodsForSurvey () {
        List<RedisCacheFood> redisCacheFoods = redisSurveyFoodUtil.getRedisCacheFoods();

        if ( !(redisCacheFoods.size() == 0) ) {
            Collections.shuffle(redisCacheFoods);
        }

        return redisCacheFoods
            .stream()
            .limit(20)
            .map(RandomFoodResponse::of)
            .collect(Collectors.toList());
    }
}
