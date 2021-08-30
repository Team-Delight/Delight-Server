package com.team.delightserver.exception.security;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

public class InvalidOauth2ProviderException extends OAuth2AuthenticationException {
    private static final String MESSAGE = "해당 제공자는 존재하지 않습니다.";

    public InvalidOauth2ProviderException() {
        super(new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST), MESSAGE);
    }
}
