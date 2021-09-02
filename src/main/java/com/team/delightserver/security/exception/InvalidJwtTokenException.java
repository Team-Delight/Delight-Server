package com.team.delightserver.security.exception;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import java.util.Arrays;

/**
 * @Created by Doe
 * @Date: 2021/08/27
 */

@Slf4j
public class InvalidJwtTokenException extends ServletException {
    private static final String MESSAGE = "Jwt 토큰이 올바르지 않습니다. jwtToken = ";

    public InvalidJwtTokenException(String jwtToken) {
        super(new ServletException(MESSAGE + jwtToken));

        log.error(this.getClass().getName() + " : " + getMessage());
        log.error(this.getClass().getName() + " : " + Arrays.toString(getStackTrace()));
    }
}