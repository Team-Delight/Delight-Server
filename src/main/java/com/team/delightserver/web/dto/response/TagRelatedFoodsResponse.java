package com.team.delightserver.web.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @Created by Doe
 * @Date: 2021/08/10
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TagRelatedFoodsResponse {
    private String name;
    private String imgUrl;
    private final Set<String> tags = new HashSet<>();

    public TagRelatedFoodsResponse(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }
}
