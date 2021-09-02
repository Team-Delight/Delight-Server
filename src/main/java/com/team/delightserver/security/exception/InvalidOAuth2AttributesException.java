package com.team.delightserver.security.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;

import java.util.Arrays;

/**
 * @Created by Doe
 * @Date: 2021/08/28
 */

@Slf4j
public class InvalidOAuth2AttributesException extends OAuth2AuthenticationException {
    private static final String MESSAGE =
            "제공자의 Attributes 가 이 OAuth2UserProviderFactory 에서 처리되지 않습니다.";

    public InvalidOAuth2AttributesException(NullPointerException nullPointerException) {
        super(
                new OAuth2Error(OAuth2ErrorCodes.UNSUPPORTED_GRANT_TYPE),
                MESSAGE,
                nullPointerException
        );

        log.error(this.getClass().getName() + " : " + getMessage());
        log.error(this.getClass().getName() + " : " + Arrays.toString(getCause().getStackTrace()));
        log.error(this.getClass().getName() + " : " + Arrays.toString(getStackTrace()));
    }

    public InvalidOAuth2AttributesException() {
        super(
                new OAuth2Error(OAuth2ErrorCodes.UNSUPPORTED_GRANT_TYPE),
                MESSAGE
        );

        log.error(this.getClass().getName() + " : " + getMessage());
        log.error(this.getClass().getName() + " : " + Arrays.toString(getStackTrace()));
    }
}
