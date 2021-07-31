package com.team.delightserver.security.handler;

import com.team.delightserver.security.factory.products.ProviderOAuth2User;
import com.team.delightserver.util.JWTUtil;
import lombok.RequiredArgsConstructor;
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
    final JWTUtil jwtUtil;

    final String JWT_SUBJECT = "user";
    final int JWT_DUE_DAY = 5;

    final String COOKIE_SUBJECT = "jwt";
    final String COOKIE_DOMAIN = "localhost";
    final int COOKIE_DUE_DAY = 5;

    final String REDIRECT_URI = "http://localhost";
    final int PORT = 3000;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        ProviderOAuth2User providerOAuth2User = (ProviderOAuth2User) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(providerOAuth2User.createJWTPayload(), JWT_SUBJECT, TimeUnit.DAYS.toMillis(JWT_DUE_DAY));

        Cookie cookie = new Cookie(COOKIE_SUBJECT, jwt);
        cookie.setSecure(true);
        cookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(COOKIE_DUE_DAY));
        cookie.setPath("/");
        cookie.setDomain(COOKIE_DOMAIN);
        response.addCookie(cookie);

        response.sendRedirect(UriComponentsBuilder
                .fromUriString(REDIRECT_URI)
                .port(PORT)
                .build().toString());
    }
}
