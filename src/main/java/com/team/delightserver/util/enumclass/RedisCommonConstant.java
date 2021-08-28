package com.team.delightserver.util.enumclass;

/**
 * @Created by Bloo
 * @Date: 2021/08/17
 */
public class RedisCommonConstant {
    public static final String SURVEY_FOODS_KEY = "cache_foods_categoryId:";
    public static final String RECOMMENDATION_RANKING_KEY = "recommendation_rankings_categoryId:";

    public static final int ONE_MIN = 60;
    public static final int THIRTY_MIN = 60 * 30;
    public static final int DEFAULT_EXPIRE_SEC = ONE_MIN;
    public static final int RANKING_EXPIRE_SEC = THIRTY_MIN;

    public static final int CACHE_FOOD_START_NUMBER = 0;
    public static final long CATEGORY_ALL_ID = 0;
    public static final long CATEGORY_KOREA_ID = 1;
    public static final long CATEGORY_JAPANESE_ID = 2;
    public static final long CATEGORY_CHINESE_ID = 3;
    public static final long CATEGORY_WESTERN_ID = 4;
    public static final long CATEGORY_SNACK_ID = 6;

    public static final String OLD_RANDOM_FOODS_KEY = "cache_foods";
}
