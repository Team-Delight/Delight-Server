package com.team.delightserver.web.domain.food;

import com.team.delightserver.web.domain.user.User;
import com.team.delightserver.web.dto.response.TagRelatedFoodsResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Created by Doe
 * @Date: 2021/08/10
 * @ModifiedDate : 2021/08/27
 */

public interface FoodRepositoryCustom {

    List<TagRelatedFoodsResponse> findAllByTagIds(List<Long> tagIds, Pageable pageable);
    List<Food> findAllByUserMypickWithinWeek(User user, Pageable pageable);
}
