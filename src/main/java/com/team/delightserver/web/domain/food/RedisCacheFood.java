package com.team.delightserver.web.domain.food;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

/**
 * @Created by Bloo
 * @Date: 2021/08/19
 */

@RedisHash ("FoodId")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RedisCacheFood {

    @Id
    private Long foodId;
    private String name;
    private String imgUrl;
}
