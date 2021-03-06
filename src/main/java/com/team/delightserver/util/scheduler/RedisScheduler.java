package com.team.delightserver.util.scheduler;

import com.team.delightserver.util.redis.RedisRecommendationRankUtil;
import com.team.delightserver.util.redis.RedisSurveyFoodUtil;
import com.team.delightserver.web.domain.food.FoodRepository;
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
public class RedisScheduler {

    private final RedisSurveyFoodUtil surveyFoodUtil;
    private final RedisRecommendationRankUtil recommendationRankRedisUtil;
    private final FoodRepository foodRepository;
    private final static String SCHEDULE_MODE = System.getProperty("schedule.mode");

    /**
     * 매일 04시에 Redis 음식 데이터 목록을 최신화
     */
    @Scheduled (cron = "0 0 04 * * *")
    public void setCacheFoods() {
        if (SCHEDULE_MODE.equals("on")) {
            log.info("********* Redis Survey Data Scheduler Start *********");
            surveyFoodUtil.deleteAllRedisCacheFoods();
            surveyFoodUtil.setCacheFoodByCategoryId();
        }
    }

    /**
     * 오후 랭킹 집계 스케줄러
     */
    @Scheduled (cron = "0 0/20 12-23 * * *")
    public void PMRankingScheduler() {
        if (SCHEDULE_MODE.equals("on")) {
            if ( recommendationRankRedisUtil.isExistRecommendationRankings() ) {
                log.info("===== Cache DB Init Delete Start ====");
                recommendationRankRedisUtil.deleteAllRedisCacheRankings();
            }
            recommendationRankRedisUtil.setRanking();
        }
    }

    /**
     * 오전 랭킹 집계 스케줄러
     */
    @Scheduled (cron = "01 0 0 * * *")
    public void AMRankingScheduler() {
        if (SCHEDULE_MODE.equals("on")) {
            if ( recommendationRankRedisUtil.isExistRecommendationRankings() ) {
                log.info("===== Cache DB Init Delete Start ====");
                recommendationRankRedisUtil.deleteAllRedisCacheRankings();
            }
            recommendationRankRedisUtil.setRanking();
        }
    }
}
