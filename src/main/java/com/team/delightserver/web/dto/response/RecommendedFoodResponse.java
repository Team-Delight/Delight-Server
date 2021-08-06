package com.team.delightserver.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @CreateBy:Min
 * @Date: 2021/07/27
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RecommendedFoodResponse {

    private List<String> foods;
}
