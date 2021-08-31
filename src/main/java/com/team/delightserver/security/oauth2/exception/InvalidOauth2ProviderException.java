package com.team.delightserver.security.oauth2.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;

import java.util.Arrays;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

@Slf4j
public class InvalidOauth2ProviderException extends OAuth2AuthenticationException {
    private static final String MESSAGE = "해당 제공자는 존재하지 않습니다.";

    public InvalidOauth2ProviderException() {
        super(new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST), MESSAGE);

        log.error(this.getClass().getName() + " : " + getMessage());
        log.error(this.getClass().getName() + " : " + Arrays.toString(getStackTrace()));
    }
}
