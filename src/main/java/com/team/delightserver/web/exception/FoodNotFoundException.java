package com.team.delightserver.web.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FoodNotFoundException extends RuntimeException{

    private static final String MESSAGE = "Food Entity가 존재하지 않습니다.";

    public FoodNotFoundException() {
        super(MESSAGE);
        log.error(MESSAGE);
    }
}
