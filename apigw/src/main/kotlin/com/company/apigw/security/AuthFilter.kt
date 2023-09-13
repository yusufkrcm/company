package com.company.apigw.security

import com.company.apigw.security.model.ValidateRequest
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
                .bodyToMono(Boolean::class.java)
                .onErrorResume { Mono.just(false) }
                .flatMap { response: Boolean ->
                    if (!response) {
                        exchange.response.setStatusCode(HttpStatus.UNAUTHORIZED)
                        return@flatMap exchange.response.setComplete()
                    }
                    chain.filter(exchange)
                }
        }

        return chain.filter(exchange)
    }

}