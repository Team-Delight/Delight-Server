package com.team.delightserver.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @CreateBy:Min
 * @Date: 2021/08/05
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RankRecommendationsResponseDto {
    private int count;
    private String name;
    private String imgUrl;
    private Long id;
}
