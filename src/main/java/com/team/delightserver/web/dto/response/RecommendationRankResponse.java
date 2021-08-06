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
    private int count;
    private String name;
    private String imgUrl;
    private Long id;

    @QueryProjection
    public RecommendationRankResponse(int count, String name, String imgUrl, Long id) {
        this.count = count;
        this.name = name;
        this.imgUrl = imgUrl;
        this.id = id;
    }
}
