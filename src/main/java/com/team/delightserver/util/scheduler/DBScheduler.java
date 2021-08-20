package com.team.delightserver.util.scheduler;

import com.team.delightserver.util.RedisUtil;
import com.team.delightserver.util.enumclass.CacheKey;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.food.FoodRepository;
import com.team.delightserver.web.domain.food.RedisCacheFood;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
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

    private final RedisUtil redisUtil;
    private final FoodRepository foodRepository;

    /**
     * 매일 04시에 Redis 음식 데이터 목록을 최신화
     */
    @Scheduled (cron = "0 0 04 * * *")
    public void setCacheFoods() {
        log.info("********* Redis DB Scheduler Start *********");
        List<RedisCacheFood> redisCacheFoods = redisUtil.getRedisCacheFoods();

        if ( !(redisCacheFoods.size() == 0) ) {
            log.info("********* Redis DB Scheduler Delete Start *********");
            redisUtil.deleteRedisCacheFoods();
        }

        List<Food> foods = foodRepository.findAll();
        log.info("********* Redis DB Scheduler Input Start *********");
        List<RedisCacheFood> newRedisCacheFoods = foods
            .stream()
            .map(Food::toRedisCacheFood)
            .collect(Collectors.toList());
        redisUtil.setRedisCacheFoods(newRedisCacheFoods);
    }
}
