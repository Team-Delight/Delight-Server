package com.team.delightserver.web.domain.recommendation;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @CreateBy:Min
 * @Date: 2021/08/02
 */

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    Recommendation findByName(@NonNull String name);
}