package com.team.delightserver.service;

import com.team.delightserver.util.DBScheduler;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.food.FoodRepository;
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
 * @CreateBy: Min, Doe
 * @Date: 2021/08/02, 2021/08/11
 * @ModifiedDate: 2021/08/17
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiFoodService {

    private final FoodRepository foodRepository;
    private final DBScheduler dbScheduler;

    @Transactional(readOnly = true)
    public List<RandomFoodsResponse> findRandomFoodsForSurvey () {
        List<Food> foods = dbScheduler.getFoods();
        Collections.shuffle(foods);
        return foods.stream()
            .limit(20)
            .map(RandomFoodsResponse::of)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TagRelatedFoodsResponse> findFoodsByTags(FindFoodsByTagsRequest findFoodsByTagsRequest, Pageable pageable) {
        return foodRepository.findAllByTagIds(findFoodsByTagsRequest.getTagIds(), pageable);
    }
}
