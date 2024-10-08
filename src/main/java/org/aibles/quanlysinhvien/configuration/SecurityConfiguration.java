//package org.aibles.quanlysinhvien.configuration;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(authz -> authz
//                        .anyRequest().permitAll())
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//                .build();
//    }
//}
//
