package com.team.delightserver.util;

import com.team.delightserver.util.enumclass.CacheKey;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.food.FoodRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Created by Bloo
 * @Date: 2021/08/18
 */
@Getter
@Slf4j
@RequiredArgsConstructor
@Component
public class DBScheduler {

    private final FoodRepository foodRepository;

    private List<Food> foods = new ArrayList<>();

    /**
     * 매일 02시에 음식 데이터 목록을 최신화
     */
    @Scheduled (cron = "0 0 16 * * *")
    @Cacheable (value = CacheKey.RANDOM_FOODS, key = "'all_foods'")
    public List<Food> setFoods() {
        foods = foodRepository.findAll();
        return foods;
    }
}
