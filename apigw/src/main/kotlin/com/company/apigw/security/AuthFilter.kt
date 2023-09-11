package com.company.apigw.security

import com.company.apigw.security.model.ValidateRequest
import com.company.apigw.security.model.ValidateResponse
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class AuthFilter(
    private val routerValidator: RouterValidator,
    private val webClient: WebClient
) : GatewayFilter {

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void>? {
        val request = exchange.request
        if (routerValidator.isApiSecured.test(request)) {
            if (!request.headers.containsKey("Authorization")) {
                exchange.response.setStatusCode(HttpStatus.UNAUTHORIZED)
                return exchange.response.setComplete()
            }

            val authorization = request.headers.getOrEmpty("Authorization")[0]
            val token = authorization.replace("Bearer ", "")

            return webClient.post()
                .uri("/auth/validate")
                .bodyValue(ValidateRequest(token))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ValidateResponse::class.java)
                .flatMap { response: ValidateResponse ->
                    val modifiedRequest = exchange.request.mutate()
                        .header("X-User-Roles", response.role)
                        .header("X-User-Username", response.username)
                        .build()
                    chain.filter(exchange.mutate().request(modifiedRequest).build())
                }
        }

        return chain.filter(exchange)
    }

}