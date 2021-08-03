package com.team.delightserver.web.dto.response;

import com.team.delightserver.web.domain.food.Recommendation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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
