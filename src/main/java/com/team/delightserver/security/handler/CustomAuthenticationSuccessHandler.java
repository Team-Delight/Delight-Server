package com.team.delightserver.security.handler;

import com.team.delightserver.security.oauth2.ProviderOAuth2User;
import com.team.delightserver.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 * @ModifiedDate: 2021/08/13
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
    @Value("${spring.frontend.domain}")
    private String COOKIE_DOMAIN;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        ProviderOAuth2User providerOAuth2User = (ProviderOAuth2User) authentication.getPrincipal();
        String jwt = JwtTokenProvider
            .generateToken(providerOAuth2User.createJWTPayload(), JWT_SUBJECT, TimeUnit.DAYS.toMillis(JWT_DUE_DAY));

        Cookie cookie = new Cookie(COOKIE_SUBJECT, jwt);
        cookie.setSecure(true);
        cookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(COOKIE_DUE_DAY));
        cookie.setPath("/");
        cookie.setDomain(COOKIE_DOMAIN);
        response.addCookie(cookie);

        response.sendRedirect(FRONTEND_URL);
    }
}
