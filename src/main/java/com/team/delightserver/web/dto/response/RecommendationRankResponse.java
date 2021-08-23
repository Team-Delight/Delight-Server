package com.team.delightserver.web.dto.response;

import java.io.Serializable;
import lombok.*;

/**
 * @CreateBy: Min, Doe
 * @Date: 2021/08/05, 2021/08/11
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RecommendationRankResponse implements Serializable {

    private Long categoryId;
    private Long recommendedCnt;
    private String name;
    private String imgUrl;

    public RecommendationRankResponse(Long count, String name, String imgUrl, Long id) {
        this.recommendedCnt = count;
        this.name = name;
        this.imgUrl = imgUrl;
        this.categoryId = id;
    }
}
