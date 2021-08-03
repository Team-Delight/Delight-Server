package com.team.delightserver.web.domain.food;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @CreateBy:Min
 * @Date: 2021/08/02
 */

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    Recommendation findByName(@NonNull String name);


}
