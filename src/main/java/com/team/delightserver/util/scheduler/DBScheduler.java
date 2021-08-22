package com.team.delightserver.util.scheduler;

import com.team.delightserver.util.redis.RedisRecommendationRankUtil;
import com.team.delightserver.util.redis.RedisSurveyFoodUtil;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.food.FoodRepository;
import com.team.delightserver.web.domain.food.RedisCacheFood;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final RedisSurveyFoodUtil surveyFoodUtil;
    private final RedisRecommendationRankUtil rankRedisUtil;
    private final FoodRepository foodRepository;

    /**
     * 매일 04시에 Redis 음식 데이터 목록을 최신화
     */
    @Scheduled (cron = "0 0 04 * * *")
    public void setCacheFoods() {
        log.info("********* Redis Survey Data Scheduler Start *********");
        List<RedisCacheFood> redisCacheFoods = surveyFoodUtil.getRedisCacheFoods();

        if ( !(redisCacheFoods.size() == 0) ) {
            log.info("********* RRedis Survey Data Scheduler Delete Start *********");
            surveyFoodUtil.deleteRedisCacheFoods();
        }

        List<Food> foods = foodRepository.findAll();
        log.info("********* Redis Survey Data Scheduler Input Start *********");
        List<RedisCacheFood> newRedisCacheFoods = foods
            .stream()
            .map(Food::toRedisCacheFood)
            .collect(Collectors.toList());
        surveyFoodUtil.setRedisCacheFoods(newRedisCacheFoods);
    }

    /**
     * 오후 랭킹 집계 스케줄러
     */
    @Scheduled (cron = "0 0/20 12-23 * * *")
    public void PMRankingScheduler() {
        if ( rankRedisUtil.isExistRecommendationRankings() ) {
            rankRedisUtil.deleteAllRedisCacheRankings();
        }
        rankRedisUtil.setRanking();
    }

    /**
     * 오전 랭킹 집계 스케줄러
     */
    @Scheduled (cron = "01 0 00 * * *")
    public void AMRankingScheduler() {
        if ( rankRedisUtil.isExistRecommendationRankings() ) {
            rankRedisUtil.deleteAllRedisCacheRankings();
        }
        rankRedisUtil.setRanking();
    }
}
