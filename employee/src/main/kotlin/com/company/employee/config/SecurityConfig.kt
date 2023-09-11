package com.company.employee.config

import com.company.employee.interceptor.AuthFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(private val filter: AuthFilter) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        return http
            .csrf { it.disable() }
            .cors { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(HttpMethod.GET, "/employee").hasAuthority("USER")
                    .anyRequest().authenticated()
            }
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

}