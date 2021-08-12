package com.team.delightserver.web.dto.response;

import com.team.delightserver.web.domain.tag.Tag;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy:Min
 * @Date: 2021/08/12
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RecommendedFoodResponse {

    private List<recommendedData> data;

    @Builder
    public RecommendedFoodResponse(List<recommendedData> data) {
        this.data = data;
    }

    public static RecommendedFoodResponse of (List<recommendedData> responseBody) {
        return RecommendedFoodResponse.builder()
                .data(responseBody)
                .build();
    }

    @Getter
    public static class recommendedData {
        private String name;
        private int score;
        private String imgUrl;
        private List<Tag> tag = new ArrayList<>();

        @Builder
        public recommendedData(String name, int score, String imgUrl) {
            this.name = name;
            this.score = score;
            this.imgUrl = imgUrl;
        }

        public static recommendedData of(String name, int score, String imgUrl) {
            return recommendedData.builder()
                    .name(name)
                    .score(score)
                    .imgUrl(imgUrl)
                    .build();
        }
    }
}
