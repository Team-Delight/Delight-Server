package com.team.delightserver.exception.security.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 * @ModifiedDate: 2021/08/13
 */

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final String PARAM_NAME = "message";
    private static final String PATH = "/login";

    @Value("${spring.frontend.url}")
    private String FRONTEND_URL;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        String encodedMessage = URLEncoder.encode(exception.getMessage(), StandardCharsets.UTF_8);

        response.sendRedirect(
                UriComponentsBuilder
                        .fromUriString(FRONTEND_URL)
                        .path(PATH)
                        .queryParam(PARAM_NAME, encodedMessage)
                        .build().toString()
        );
    }
}


