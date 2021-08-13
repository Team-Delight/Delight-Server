package com.team.delightserver.web.domain.foodtag;

import com.team.delightserver.web.domain.tag.Tag;

import java.util.List;

/**
 * @CreateBy:Min
 * @Date: 2021/08/13
 */

public interface FoodTagRepositoryCustom {

    List<Tag> findAllTagsByFoodName(String foodName);
}
