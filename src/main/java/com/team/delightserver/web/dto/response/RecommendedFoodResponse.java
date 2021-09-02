package com.team.delightserver.web.dto.response;

import com.team.delightserver.web.domain.tag.Tag;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public static RecommendedFoodResponse of(List<recommendedData> responseBody) {
        return RecommendedFoodResponse.builder()
                .data(responseBody)
                .build();
    }

    @Getter
    public static class recommendedData {
        private String name;
        private Double score;
        private String imgUrl;
        private List<Tag> tag;

        @Builder
        public recommendedData(String name, Double score, String imgUrl, List<Tag> tag) {
            this.name = name;
            this.score = score;
            this.imgUrl = imgUrl;
            this.tag = tag;
        }

        public static recommendedData of(String name, Double score,
                                         String imgUrl, List<Tag> tag) {
            return recommendedData.builder()
                    .name(name)
                    .score(score)
                    .imgUrl(imgUrl)
                    .tag(tag)
                    .build();
        }
    }
}
