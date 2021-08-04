package com.team.delightserver.security.handler;

import com.team.delightserver.security.factory.products.ProviderOAuth2User;
import com.team.delightserver.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

@RequiredArgsConstructor
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final String JWT_SUBJECT = "user";
    private static final int JWT_DUE_DAY = 5;

    private static final String COOKIE_SUBJECT = "jwt";
    private static final int COOKIE_DUE_DAY = 5;

    @Value("${spring.frontend.url}")
    private String FRONTEND_URL;
    @Value("${spring.frontend.port}")
    private Integer FRONTEND_PORT;
    @Value("${spring.frontend.domain}")
    private String COOKIE_DOMAIN;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        ProviderOAuth2User providerOAuth2User = (ProviderOAuth2User) authentication.getPrincipal();
        String jwt = JWTUtil.generateToken(providerOAuth2User.createJWTPayload(), JWT_SUBJECT, TimeUnit.DAYS.toMillis(JWT_DUE_DAY));

        Cookie cookie = new Cookie(COOKIE_SUBJECT, jwt);
        cookie.setSecure(true);
        cookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(COOKIE_DUE_DAY));
        cookie.setPath("/");
        cookie.setDomain(COOKIE_DOMAIN);
        response.addCookie(cookie);

        response.sendRedirect(UriComponentsBuilder
                .fromUriString(FRONTEND_URL)
                .port(FRONTEND_PORT)
                .build().toString());
    }
}
