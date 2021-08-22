package com.team.delightserver.util.scheduler;

import com.team.delightserver.util.RedisUtil;
import com.team.delightserver.web.domain.food.Food;
import com.team.delightserver.web.domain.food.FoodRepository;
import com.team.delightserver.web.domain.food.RedisCacheFood;
import com.team.delightserver.web.domain.recommendation.RecommendationRepository;
import com.team.delightserver.web.dto.response.RecommendationRankResponse;
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

    private static final long CATEGORY_ALL_ID = 0;
    private static final long CATEGORY_KOREA_ID = 1;
    private static final long CATEGORY_JAPANESE_ID = 2;
    private static final long CATEGORY_CHINESE_ID = 3;
    private static final long CATEGORY_WESTERN_ID = 4;
    private static final long CATEGORY_FAST_FOOD_ID = 5;
    private static final long CATEGORY_SNACK_ID = 6;


    private final RedisUtil redisUtil;
    private final FoodRepository foodRepository;
    private final RecommendationRepository recommendationRepository;

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

    @Scheduled (cron = "0 0/3 * * * *")
    public void refreshCacheRanking() {
        List<RecommendationRankResponse> rankResponses = redisUtil.getRedisRecommendationRankings();
        if ( !(rankResponses.size() == 0)) {
            redisUtil.deleteRedisCacheRankings();
        }
        setRanking();
    }

    private void setRanking() {
        log.info("================ Set Ranking Start ==========");
        redisUtil.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(CATEGORY_ALL_ID), CATEGORY_ALL_ID
        );

        redisUtil.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(CATEGORY_KOREA_ID), CATEGORY_KOREA_ID
        );

        redisUtil.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(CATEGORY_JAPANESE_ID), CATEGORY_JAPANESE_ID
        );

        redisUtil.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(CATEGORY_CHINESE_ID), CATEGORY_CHINESE_ID
        );

        redisUtil.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(CATEGORY_WESTERN_ID), CATEGORY_WESTERN_ID
        );

        redisUtil.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(CATEGORY_FAST_FOOD_ID), CATEGORY_FAST_FOOD_ID
        );

        redisUtil.setRedisRankingResponseByCategoryId(
            recommendationRepository.findAllTopTenByCategoryId(CATEGORY_SNACK_ID), CATEGORY_SNACK_ID
        );
    }

    private void deleteRanking() {
        redisUtil.deleteRedisCacheRankings();
    }
}
