package com.team.delightserver.web.domain.food;

import com.team.delightserver.web.dto.request.FindFoodsByTagsRequest;
import com.team.delightserver.web.dto.response.TagRelatedFoodsResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Created by Doe
 * @Date: 2021/08/10
 * @ModifiedDate : 2021/08/17
 */

public interface FoodRepositoryCustom {

    List<TagRelatedFoodsResponse> findFoodsByTags(FindFoodsByTagsRequest findFoodsByTagsRequest, Pageable pageable);
}
