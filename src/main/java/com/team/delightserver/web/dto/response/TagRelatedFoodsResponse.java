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
    private final Set<TagResponse> tags = new HashSet<>();

    public TagRelatedFoodsResponse(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    @Getter
    public static class FindAllByTagQueryResult {
        private final Long id;
        private final String name;
        private final String imgUrl;
        private final String tag;
        private final Long tagId;

        public FindAllByTagQueryResult(Long id, String name, String imgUrl, String tag, Long tagId) {
            this.id = id;
            this.name = name;
            this.imgUrl = imgUrl;
            this.tag = tag;
            this.tagId = tagId;
        }
    }
}
