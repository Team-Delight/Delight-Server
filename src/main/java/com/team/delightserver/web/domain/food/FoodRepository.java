package com.team.delightserver.web.domain.food;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Created by Bloo, Min
 * @Date: 2021/08/05, 2021/08/02
 */

public interface FoodRepository extends JpaRepository<Food, Long>, FoodRepositoryCustom {

    @Query(value = "select * from FOOD order by RAND() LIMIT 20", nativeQuery = true)
    List<Food> findAllRandom();

    Optional<Food> findByName(String name);
}
