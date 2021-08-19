package com.team.delightserver.util.enumclass;

/**
 * @Created by Bloo
 * @Date: 2021/08/17
 */
public class CacheKey {
    public static final int ONE_MIN = 60;
    public static final int THIRTY_MIN = 60 * 30;
    public static final String RANDOM_FOODS = "cache_foods";
    public static final int DEFAULT_EXPIRE_SEC = ONE_MIN;
    public static final int RANKING_EXPIRE_SEC = THIRTY_MIN;
}
