//package com.ssafy.enjoyTrip.config;
//
//import com.ssafy.enjoyTrip.common.interceptor.ConfirmInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//
//    private static final String[] DOC_URLS = {
//            "/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html","/swagger-ui/**"
//    };
//
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new ConfirmInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/users", "/users/login", "/", "/index.html", "/swagger-ui.html")
//                .excludePathPatterns(DOC_URLS); // redirect도 포함된다.
//    }
//}
