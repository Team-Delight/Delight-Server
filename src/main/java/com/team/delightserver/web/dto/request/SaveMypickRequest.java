package com.team.delightserver.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @CreateBy: Doe
 * @Date: 2021/08/21
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveMypickRequest {

    @JsonProperty("food_name")
    private String foodName;
}
