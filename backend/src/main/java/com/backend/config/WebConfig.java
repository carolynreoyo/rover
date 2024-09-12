package com.backend.config;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig {


    private static final Long MAX_AGE = 3600L;
    private static final int CORS_FILTER_ORDER = -102;
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //Make the below setting as * to allow connection from any hos
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods(HttpMethod.GET.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.DELETE.name())
                        .allowedHeaders(HttpHeaders.CONTENT_TYPE,
                                HttpHeaders.AUTHORIZATION);
            }
        };
    }

    // @Bean
    // public FilterRegistrationBean corsFilter() {
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     CorsConfiguration config = new CorsConfiguration();
    //     config.setAllowCredentials(true);
    //     config.addAllowedOrigin("http://localhost:3000");
    //     config.setAllowedHeaders(Arrays.asList(
    //             HttpHeaders.AUTHORIZATION,
    //             HttpHeaders.CONTENT_TYPE,
    //             HttpHeaders.ACCEPT));
    //     config.setAllowedMethods(Arrays.asList(
    //             HttpMethod.GET.name(),
    //             HttpMethod.POST.name(),
    //             HttpMethod.PUT.name(),
    //             HttpMethod.DELETE.name()));
    //     config.setMaxAge(MAX_AGE);
    //     source.registerCorsConfiguration("/**", config);
    //     FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));

    //     // should be set order to -100 because we need to CorsFilter before SpringSecurityFilter
    //     bean.setOrder(CORS_FILTER_ORDER);
    //     return bean;
    // }
        @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // .exceptionHandling((exception)-> exception.authenticationEntryPoint(userAuthenticationEntryPoint))
                // .addFilterBefore(new AuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                        .anyRequest().authenticated())
                              .csrf(AbstractHttpConfigurer::disable);
        ;
        return http.build();
    }
}