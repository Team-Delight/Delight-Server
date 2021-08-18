package com.team.delightserver.web.domain.recommendation;

import com.team.delightserver.web.dto.response.RecommendationRankResponse;

import java.util.List;

/**
 * @CreateBy:Min
 * @Date: 2021/08/18
 */

public interface RecommendationRepositoryCustom {
    List<RecommendationRankResponse> findAllTopTenByCategoryId(Long id);
}
