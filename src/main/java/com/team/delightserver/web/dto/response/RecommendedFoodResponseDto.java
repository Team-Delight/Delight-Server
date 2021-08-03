package com.team.delightserver.web.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @CreateBy:Min
 * @Date: 2021/07/27
 */

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RecommendedFoodResponseDto {

    private List<String> foods;
}
