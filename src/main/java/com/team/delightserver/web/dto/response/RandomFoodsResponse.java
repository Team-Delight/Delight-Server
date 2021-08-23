package com.team.delightserver.web.dto.response;

import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.food.RedisCacheFood;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Created by Bloo
 * @Date: 2021/08/05
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RandomFoodsResponse implements Serializable {

    private String name;

    private String imgUrl;

    @Builder
    public RandomFoodsResponse ( String name, String imgUrl ) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public static RandomFoodsResponse of ( RedisCacheFood redisCacheFood ) {
        return RandomFoodsResponse.builder()
            .name(redisCacheFood.getName())
            .imgUrl(redisCacheFood.getImgUrl())
            .build();
    }

    public static RandomFoodsResponse dbOf ( Food food ) {
        return RandomFoodsResponse.builder()
            .name(food.getName())
            .imgUrl(food.getImgUrl())
            .build();
    }
}
