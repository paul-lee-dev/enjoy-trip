//package com.ssafy.enjoyTrip.config;
//
//import com.ssafy.enjoyTrip.common.constant.Role;
//import com.ssafy.enjoyTrip.user.service.UserServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final UserServiceImpl userService;
//
//    public SecurityConfig(UserServiceImpl userService) {
//        this.userService = userService;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/user/login").permitAll()
//                .antMatchers("/plan/**").hasRole(Role.USER.name())
//                .anyRequest().authenticated()
//                .and()
//                .logout().logoutSuccessUrl("/")
//                .and()
//                .oauth2Login()
//                .userInfoEndpoint()
//                .userService(userService);
//
//        return http.build();
//    }
//
//}
