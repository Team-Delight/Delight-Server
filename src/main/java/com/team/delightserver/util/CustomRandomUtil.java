package com.team.delightserver.util;

import java.util.Random;

/**
 * @Created by Doe
 * @Date: 2021/08/29
 */

public class CustomRandomUtil {

    public static final char[] lowerCase = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public static final char[] upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    public static final char[] numbers = "0123456789".toCharArray();
    public static final char[] azAZ09 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    public static String getRandomString(){
        return getRandomString(azAZ09,10);
    }

    public static String getRandomString(char[] fromChars, int length){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(fromChars[random.nextInt(fromChars.length)]);
        }
        return stringBuilder.toString();
    }
}
