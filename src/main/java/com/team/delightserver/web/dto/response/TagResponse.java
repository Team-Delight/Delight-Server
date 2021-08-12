package com.team.delightserver.web.dto.response;

import lombok.Getter;

/**
 * @Created by Doe
 * @Date: 2021/08/10
 */

@Getter
public class TagResponse {
    private final Long id;
    private final String name;

    public TagResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
