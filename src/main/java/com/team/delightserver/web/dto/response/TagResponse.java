package com.team.delightserver.web.dto.response;

import com.team.delightserver.web.domain.tag.Tag;
import lombok.Getter;

/**
 * @Created by Doe
 * @Date: 2021/08/13
 */

@Getter
public class TagResponse {
    private final Long id;
    private final String name;

    public TagResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static TagResponse of(Tag tag) {
        return new TagResponse(tag.getId(), tag.getName());
    }
}
