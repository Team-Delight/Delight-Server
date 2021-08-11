package com.team.delightserver.web.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

/**
 * @CreateBy:Min
 * @Date: 2021/08/05
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RecommendationRankResponse {
    private Long categoryId;
    private String name;
    private int recommendedCnt;
    private String imgUrl;

    public RecommendationRankResponse(int count, String name, String imgUrl, Long id) {
        this.recommendedCnt = count;
        this.name = name;
        this.imgUrl = imgUrl;
        this.categoryId = id;
    }
}
