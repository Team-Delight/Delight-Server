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
public class RandomFoodResponse implements Serializable {

    private String name;
    private String imgUrl;

    @Builder
    public RandomFoodResponse ( String name, String imgUrl ) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public static RandomFoodResponse of ( RedisCacheFood redisCacheFood ) {
        return RandomFoodResponse.builder()
            .name(redisCacheFood.getName())
            .imgUrl(redisCacheFood.getImgUrl())
            .build();
    }
}
