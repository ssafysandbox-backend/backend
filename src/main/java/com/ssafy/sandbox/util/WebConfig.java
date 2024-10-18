package com.ssafy.sandbox.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${front.url}")
    private String FRONT_URL;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println(FRONT_URL);
        registry.addMapping("/**") // 모든 경로에 대해
                .allowedOrigins(FRONT_URL)
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
