package com.team.delightserver.web.dto.request;

import lombok.*;

import java.util.List;

/**
 * @CreateBy:Min
 * @Date: 2021/07/27
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SelectedFoodRequestDto {

    private List<String> food;

}
