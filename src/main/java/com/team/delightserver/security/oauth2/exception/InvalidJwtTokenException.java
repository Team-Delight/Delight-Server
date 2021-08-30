package com.team.delightserver.security.oauth2.exception;

import javax.servlet.ServletException;

/**
 * @Created by Doe
 * @Date: 2021/08/27
 */

public class InvalidJwtTokenException extends ServletException {
    private static final String MESSAGE = "Jwt 토큰이 올바르지 않습니다. jwtToken = ";

    public InvalidJwtTokenException(String jwtToken) {
        super(new ServletException(MESSAGE + jwtToken));
    }
}
