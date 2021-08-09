package com.team.delightserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 */

@EnableWebMvc
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Value("${spring.frontend.url}")
    private String FRONTEND_URL;
    @Value("${spring.frontend.port}")
    private Integer FRONTEND_PORT;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String corsUrl = UriComponentsBuilder
                .fromUriString(FRONTEND_URL).port(FRONTEND_PORT).build().toString();

        // TODO : Deploy 된 이후에 allowedOrigins 를 corsUrl 로 정확히 하고 바꾸기
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}