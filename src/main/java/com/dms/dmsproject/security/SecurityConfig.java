package com.dms.dmsproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF (because we are using REST APIs)
            .csrf(csrf -> csrf.disable())

            // No session (JWT is stateless)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/loginpage/**",      // login APIs
                        "/registration/**"   // registration APIs
                        //"/otpapi/**"          // otp APIs
                ).permitAll()
                .anyRequest().authenticated()
            )

            // Add JWT filter before UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}