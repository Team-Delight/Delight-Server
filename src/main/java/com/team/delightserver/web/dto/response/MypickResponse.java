package com.team.delightserver.web.dto.response;

import com.team.delightserver.web.domain.tag.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Created by Min
 * @Date: 2021/08/19
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MypickResponse {
    private List<Tag> foodTag;
    private String timeType;
    private String name;
    private String imgUrl;
    private LocalDateTime createdAt;

    @Builder
    public MypickResponse(List<Tag> foodTag, MypickWithoutTagsResponse mypickWithoutTagsResponse) {
        this.foodTag = foodTag;
        this.timeType = mypickWithoutTagsResponse.getTimeType();
        this.name = mypickWithoutTagsResponse.getName();
        this.imgUrl = mypickWithoutTagsResponse.getImgUrl();
        this.createdAt = mypickWithoutTagsResponse.getCreatedAt();
    }

    public static MypickResponse of(List<Tag> foodTag, MypickWithoutTagsResponse mypickWithoutTagsResponse) {
        return builder()
                .foodTag(foodTag)
                .mypickWithoutTagsResponse(mypickWithoutTagsResponse)
                .build();
    }

    @Getter
    public static class MypickWithoutTagsResponse {

        private String timeType;
        private String name;
        private String imgUrl;
        private LocalDateTime createdAt;

        @Builder
        public MypickWithoutTagsResponse(String timeType, String name,
                                         String imgUrl, LocalDateTime createdAt) {
            this.timeType = timeType;
            this.name = name;
            this.imgUrl = imgUrl;
            this.createdAt = createdAt;
        }

        public static MypickWithoutTagsResponse of(String timeType, String imgUrl,
                                                   String name, LocalDateTime createdAt) {
            return builder()
                    .name(name)
                    .imgUrl(imgUrl)
                    .timeType(timeType)
                    .createdAt(createdAt)
                    .build();
        }
    }
}
