//package com.ssafy.enjoyTrip.config;
//
//import com.ssafy.enjoyTrip.user.OAuth2SuccessHandler;
//import com.ssafy.enjoyTrip.user.service.UserServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final UserServiceImpl userService;
//    private final OAuth2SuccessHandler oAuth2SuccessHandler;
//
//    public SecurityConfig(UserServiceImpl userService, OAuth2SuccessHandler oAuth2SuccessHandler) {
//        this.userService = userService;
//        this.oAuth2SuccessHandler = oAuth2SuccessHandler;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .headers().frameOptions().disable()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/", "/users/login", "/users").permitAll()
//                .antMatchers("/plans/**").hasRole("USER")
//                .anyRequest().authenticated()
//                .and()
//                .logout().logoutSuccessUrl("/")
//                .and()
//                .oauth2Login()
//                .successHandler(oAuth2SuccessHandler)
//                .userInfoEndpoint()
//                .userService(userService);
//
//        return http.build();
//    }
//
//}
