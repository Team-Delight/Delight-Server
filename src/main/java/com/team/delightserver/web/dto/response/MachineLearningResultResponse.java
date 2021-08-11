package com.team.delightserver.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @CreateBy:Min
 * @Date: 2021/08/12
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MachineLearningResultResponse {

    private List<String> foods;
    private List<Integer> scores;
}
