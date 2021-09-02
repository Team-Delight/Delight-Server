package com.team.delightserver.exception;

import lombok.extern.slf4j.Slf4j;

/**
 *  @Created by Bloo
 *  @Date: 2021/09/01
 */
@Slf4j
public class FoodNotFoundException extends RuntimeException {
    private static final String MESSAGE = "잘못된 FOOD 입니다.";
    public FoodNotFoundException () {
        super(MESSAGE);
    }
}
