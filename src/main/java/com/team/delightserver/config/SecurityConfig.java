package com.team.delightserver.config;

import com.team.delightserver.security.handler.CustomAuthenticationFailureHandler;
import com.team.delightserver.security.handler.CustomAuthenticationSuccessHandler;
import com.team.delightserver.security.CustomOAuth2UserService;
import com.team.delightserver.security.jwt.filter.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;

/**
 * @Created by Doe
 * @Date: 2021/07/29
 */

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Value("${spring.frontend.url}")
    private String FRONTEND_URL;
    @Value("${spring.frontend.port}")
    private Integer FRONTEND_PORT;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().configurationSource(request -> {
            CorsConfiguration cors = new CorsConfiguration();
            String corsUrl = UriComponentsBuilder
                    .fromUriString(FRONTEND_URL).port(FRONTEND_PORT).build().toString();

            cors.setAllowedOrigins(Arrays.asList(corsUrl, "http://localhost:3000"));
            cors.setAllowedMethods(Collections.singletonList("*"));
            cors.setAllowedHeaders(Collections.singletonList("*"));
            cors.setAllowCredentials(true);

            return cors;
        });

        http.headers().frameOptions().sameOrigin();

        http.csrf().disable()
            .authorizeRequests().antMatchers("/", "/h2-console", "/api/foods").permitAll()
            .antMatchers("/restricted").authenticated().and()
            .logout().logoutSuccessUrl("/");

        http.addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);

        http.exceptionHandling()
            .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        http.oauth2Login()
            .successHandler(customAuthenticationSuccessHandler)
            .failureHandler(customAuthenticationFailureHandler)
            .userInfoEndpoint().userService(customOAuth2UserService);
    }
}