package com.company.apigw.config

import com.company.apigw.security.AuthFilter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GatewayConfig(private val filter: AuthFilter) {
    @Bean
    fun routes(builder: RouteLocatorBuilder): RouteLocator? {
        return builder.routes()
            .route("user")
            { r: PredicateSpec -> r.path("/user/**").filters { f -> f.filter(filter) }.uri("http://localhost:8083") }
            .route("user")
            { r: PredicateSpec -> r.path("/auth/**").filters { f-> f.filter(filter) }.uri("http://localhost:8083") }
            .route("employee")
            { r: PredicateSpec -> r.path("/employee/**").filters { f -> f.filter(filter) }.uri("http://localhost:8081") }
            .route("product")
            { r: PredicateSpec -> r.path("/product/**").filters { f -> f.filter(filter) }.uri("http://localhost:8082") }
            .build()
    }

}