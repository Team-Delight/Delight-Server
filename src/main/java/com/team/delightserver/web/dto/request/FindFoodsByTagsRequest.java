package com.team.delightserver.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy: Doe
 * @Date: 2021/08/17
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindFoodsByTagsRequest {

    @JsonProperty("tag_ids")
    private final List<Long> tagIds = new ArrayList<>();

}
