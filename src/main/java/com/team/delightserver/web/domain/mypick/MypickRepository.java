package com.team.delightserver.web.domain.mypick;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @CreateBy:Doe, Min
 * @Date: 2021/08/18
 */

public interface MypickRepository extends JpaRepository<Mypick, Long>,MypickRepositoryCustom {
}

