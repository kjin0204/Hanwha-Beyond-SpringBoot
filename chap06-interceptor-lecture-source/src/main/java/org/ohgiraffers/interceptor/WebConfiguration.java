package org.ohgiraffers.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* 설명. WebMvcConfigurer 관련 설정 : Interceptor 추가 및 static(정적) 리소스 호출 경로 등록 설정 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private StopwatchInterceptor stopwatchInterceptor;

    @Autowired
    public WebConfiguration(StopwatchInterceptor stopwatchInterceptor) {
        this.stopwatchInterceptor = stopwatchInterceptor;
    }

    /* 설명. 인터셉터 추가 및 동작 요청 패턴 설정 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(stopwatchInterceptor)
                .excludePathPatterns("/css/**"); // 이런 형식의 요청은 인터셉터가 동작하지 않도록 함
    }

    /* 설명. 정적 리소스 호출을 위한 설정 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:static/css/")
                .setCachePeriod(100); // 응답 속도 향상을 위한 캐싱 시간 설정
    }
}
