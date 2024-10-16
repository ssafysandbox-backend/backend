package com.ssafy.todolist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Spring 서버 전역적으로 CORS 설정
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS 허용
                .allowedOrigins("https://ssafysandbox.vercel.app") // 허용할 클라이언트 도메인
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE") // 허용할 HTTP 메서드
                .allowedHeaders("Content-Type") // 허용할 헤더
                .allowCredentials(false) // 쿠키 사용 여부 (필요 시 true로 설정)
                .maxAge(3600); // Preflight 요청 캐시 시간 (1시간)
    }
}