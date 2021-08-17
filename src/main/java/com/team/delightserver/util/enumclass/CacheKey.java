package com.team.delightserver.util.enumclass;

/**
 * @Created by Bloo
 * @Date: 2021/08/17
 */
public class CacheKey {
    public static final int THREE_SEC = 3;
    public static final int ONE_MIN = 60;
    public static final int TEN_MIN = 60 * 10;
    public static final int ONE_DAY = 60 * 60 * 24;

    public static final int DEFAULT_EXPIRE_SEC = ONE_MIN;
    public static final int RANKING_EXPIRE_SEC = TEN_MIN;
    public static final int RANDOM_FOODS_EXPIRE_SEC = THREE_SEC;
    public static final String RANDOM_FOODS = "randomFoods";
}
