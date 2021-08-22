package com.team.delightserver.service;

import com.team.delightserver.util.RedisUtil;
import com.team.delightserver.web.domain.food.FoodRepository;
import com.team.delightserver.web.domain.food.RedisCacheFood;
import com.team.delightserver.web.dto.request.FindFoodsByTagsRequest;
import com.team.delightserver.web.dto.response.RandomFoodsResponse;
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
    private final RedisUtil redisUtil;

    @Transactional(readOnly = true)
    public List<RandomFoodsResponse> findRandomFoodsForSurvey () {
        log.info("********* findRandomFood  Start *********");
        List<RedisCacheFood> redisCacheFoods = redisUtil.getRedisCacheFoods();

        if ( !(redisCacheFoods.size() == 0) ) {
            Collections.shuffle(redisCacheFoods);
        }

        return redisCacheFoods
            .stream()
            .limit(20)
            .map(RandomFoodsResponse::of)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TagRelatedFoodsResponse> findFoodsByTags(FindFoodsByTagsRequest findFoodsByTagsRequest, Pageable pageable) {
        return foodRepository.findAllByTagIds(findFoodsByTagsRequest.getTagIds(), pageable);
    }
}
