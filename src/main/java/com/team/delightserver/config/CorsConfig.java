package com.team.delightserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

@EnableWebMvc
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    // TODO : deploy 이후 CORS_URL 변경, 외부 CONFIG 로 리펙토링
    private final String CORS_URL = "http://localhost:3000";
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(CORS_URL)
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}