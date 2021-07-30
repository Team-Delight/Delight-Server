package com.team.delightserver.security.handler;

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
 */

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    final String REDIRECT_URI = "http://localhost";
    final String PARAM_NAME = "message";
    final String PATH = "/login";
    final int PORT = 3000;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        String encodedMessage = URLEncoder.encode(exception.getMessage(), StandardCharsets.UTF_8);

        response.sendRedirect(
                UriComponentsBuilder
                        .fromUriString(REDIRECT_URI)
                        .port(PORT)
                        .path(PATH)
                        .queryParam(PARAM_NAME, encodedMessage)
                        .build().toString()
        );
    }
}


