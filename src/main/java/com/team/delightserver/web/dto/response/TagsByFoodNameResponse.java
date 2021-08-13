package com.team.delightserver.web.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @CreateBy:Min
 * @Date: 2021/08/13
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TagsByFoodNameResponse {
    private Long id;
    private Long name;

    public TagsByFoodNameResponse(Long id, Long name) {
        this.id = id;
        this.name = name;
    }
}
