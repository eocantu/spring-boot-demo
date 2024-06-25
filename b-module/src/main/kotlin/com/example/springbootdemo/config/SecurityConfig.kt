package com.example.springbootdemo.config

import com.example.springbootdemo.security.AuthHeaderWebExchangeMatcher
import org.springframework.context.annotation.Bean
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter

class SecurityConfig {
    @Bean
    fun securityWebFilterChain(
        http: ServerHttpSecurity,
        tokenAuthFilter: AuthenticationWebFilter,
        authHeaderWebExchangeMatcher: AuthHeaderWebExchangeMatcher
    ): SecurityWebFilterChain {
        return http
            .csrf().disable()
            .addFilterAt(tokenAuthFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .authorizeExchange()
            .pathMatchers("/playground").permitAll()
            .pathMatchers("/warmup").permitAll()
            .pathMatchers("/isActive").permitAll()
            .pathMatchers("/actuator/**").permitAll()
            .matchers(authHeaderWebExchangeMatcher).permitAll()
            .anyExchange().authenticated()
            .and()
            .build()
    }
}