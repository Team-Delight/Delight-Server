package com.team.delightserver.service;

import com.team.delightserver.web.domain.food.FoodRepository;
import com.team.delightserver.web.dto.request.FindFoodsByTagsRequest;
import com.team.delightserver.web.dto.response.RandomFoodsResponse;
import com.team.delightserver.web.dto.response.TagRelatedFoodsResponse;
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

    @Transactional (readOnly = true)
    public List<RandomFoodsResponse> findRandomFoodsForSurvey () {
        return foodRepository.findAllRandom()
            .stream()
            .map(RandomFoodsResponse::of)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TagRelatedFoodsResponse> findFoodsByTags(FindFoodsByTagsRequest findFoodsByTagsRequest, Pageable pageable) {
        return foodRepository.findFoodsByTags(findFoodsByTagsRequest, pageable);
    }
}
