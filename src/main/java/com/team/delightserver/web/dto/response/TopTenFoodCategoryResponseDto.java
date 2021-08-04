package com.team.delightserver.web.dto.response;

import lombok.*;

/**
 * @CreateBy:Min
 * @Date: 2021/08/05
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TopTenFoodCategoryResponseDto {
    private int count;
    private String name;
    private String imgUrl;
    private Long id;
}

