package com.backend.config;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    // private final UserAuthenticationProvider userAuthenticationProvider;
    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {
    //     CorsConfiguration corsConfiguration = new CorsConfiguration();
    //     //Make the below setting as * to allow connection from any hos
    //     corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
    //     corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    //     corsConfiguration.setAllowCredentials(true);
    //     corsConfiguration.setAllowedHeaders(List.of("*"));
    //     corsConfiguration.setMaxAge(3600L);
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", corsConfiguration);
    //     return source;
    // }
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //             .exceptionHandling((exception)-> exception.authenticationEntryPoint(userAuthenticationEntryPoint))
    //             .addFilterBefore(new AuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
    //             .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))
    //             .authorizeHttpRequests((requests) -> requests
    //                     .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
    //                     .anyRequest().authenticated())
    //     ;
    //     return http.build();
    // }

}