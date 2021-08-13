package com.team.delightserver.web.domain.foodtag;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @CreateBy:Min
 * @Date: 2021/08/13
 */

public interface FoodTagRepository
        extends JpaRepository<FoodTag, Long>, FoodTagRepositoryCustom {

}