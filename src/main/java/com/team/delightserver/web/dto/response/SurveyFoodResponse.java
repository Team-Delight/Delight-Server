package com.team.delightserver.web.dto.response;

import com.team.delightserver.web.domain.food.RedisCacheFood;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Created by Bloo
 * @Date: 2021/08/05
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SurveyFoodResponse implements Serializable {

    private Long categoryId;
    private String name;
    private String imgUrl;

    @Builder
    public SurveyFoodResponse ( Long categoryId, String name, String imgUrl ) {
        this.categoryId = categoryId;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public static SurveyFoodResponse of ( RedisCacheFood redisCacheFood ) {
        return SurveyFoodResponse.builder()
            .categoryId(redisCacheFood.getCategoryId())
            .name(redisCacheFood.getName())
            .imgUrl(redisCacheFood.getImgUrl())
            .build();
    }
}
