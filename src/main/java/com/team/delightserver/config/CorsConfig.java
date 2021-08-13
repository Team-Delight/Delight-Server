package com.team.delightserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Created by Doe
 * @Date: 2021/07/30
 * @ModifiedDate: 2021/08/13
 */

@EnableWebMvc
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Value("${spring.frontend.url}")
    private String FRONTEND_URL;
    @Value("${spring.develop.url}")
    private String DEVELOP_URL;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins(FRONTEND_URL, DEVELOP_URL)
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}