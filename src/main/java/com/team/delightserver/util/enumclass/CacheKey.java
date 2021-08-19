package com.team.delightserver.util.enumclass;

/**
 * @Created by Bloo
 * @Date: 2021/08/17
 */
public class CacheKey {
    public static final int THREE_SEC = 3;
    public static final int ONE_MIN = 60;
    public static final int THIRTY_MIN = 60 * 30;
    public static final int HOUR = 60 * 60;
    public static final int ONE_DAY = 60 * 60 * 24;

    public static final int DEFAULT_EXPIRE_SEC = ONE_MIN;
    public static final int RANKING_EXPIRE_SEC = THIRTY_MIN;
    public static final int RANDOM_FOODS_EXPIRE_SEC = HOUR;
    public static final String RANDOM_FOODS = "randomFoods";
}
