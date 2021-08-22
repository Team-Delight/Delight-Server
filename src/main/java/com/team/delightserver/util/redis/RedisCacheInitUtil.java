package com.team.delightserver.util.redis;

import com.team.delightserver.util.redis.RedisRecommendationRankUtil;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Created by Bloo
 * @Date: 2021/08/22
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RedisCacheInitUtil {

    private final RedisRecommendationRankUtil recommendationRankRedisUtil;

    @PostConstruct
    public void redisCacheInit() {
        if ( recommendationRankRedisUtil.isExistRecommendationRankings() ) {
            log.info("===== Cache DB Init Delete Start ====");
            recommendationRankRedisUtil.deleteAllRedisCacheRankings();
        }
        recommendationRankRedisUtil.setRanking();
    }
}
