package com.company.apigw.security

import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import java.util.function.Predicate

@Component
class RouterValidator {

    val openApiEndpoints = listOf(
        "/auth/register",
        "/auth/login",
        "/auth/validate"
    )

    var isApiSecured = Predicate { request: ServerHttpRequest ->
        openApiEndpoints
            .stream()
            .noneMatch { uri: String? ->
                request.uri.path.contains(uri!!)
            }
    }

}