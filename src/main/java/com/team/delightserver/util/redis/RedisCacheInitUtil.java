package com.team.delightserver.util.redis;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Created by Bloo, Doe
 * @Date: 2021/08/22
 * @ModifiedDate : 2021/08/27
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RedisCacheInitUtil {

    private final RedisRecommendationRankUtil recommendationRankRedisUtil;
    public final static String SCHEDULE_MODE = System.getProperty("schedule.mode");

    @PostConstruct
    public void redisRankingCacheInit() {
        if (SCHEDULE_MODE != null && SCHEDULE_MODE.equals("on")) {
            if ( recommendationRankRedisUtil.isExistRecommendationRankings() ) {
                log.info("===== Cache DB Init Delete Start ====");
                recommendationRankRedisUtil.deleteAllRedisCacheRankings();
            }
            recommendationRankRedisUtil.setRanking();
        }
    }
}
