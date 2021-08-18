package com.team.delightserver.web.domain.recommendation;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @CreateBy:Min
 * @Date: 2021/08/02
 * @ModifiedDate: 2021/08/18
 */

public interface RecommendationRepository extends JpaRepository<Recommendation, Long>, RecommendationRepositoryCustom {
}
