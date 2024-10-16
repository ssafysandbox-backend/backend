package com.ssafy.todo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${front.url}")
    private String FRONT_URL;

    @Value("${back.pattern}")
    private String PATTERN;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(PATTERN)
                .allowedOrigins(FRONT_URL)
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name())
                .allowCredentials(true);
    }
}
