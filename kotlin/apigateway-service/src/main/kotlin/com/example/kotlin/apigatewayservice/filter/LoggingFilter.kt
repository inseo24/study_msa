package com.example.kotlin.apigatewayservice.filter

import mu.KLogging
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.Ordered
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


@Component
class LoggingFilter() : AbstractGatewayFilterFactory<LoggingFilter.Config>(Config::class.java) {

    companion object : KLogging()

    override fun apply(config: Config): GatewayFilter? {

        return OrderedGatewayFilter({ exchange: ServerWebExchange, chain: GatewayFilterChain ->
            val request: ServerHttpRequest = exchange.request
            val response: ServerHttpResponse = exchange.response
            logger.info("Logging Filter baseMessage: {}", config.baseMessage)
            if (config.preLogger) {
                logger.info("Logging PRE Filter: request id -> {}", request.id)
            }
            chain.filter(exchange).then(Mono.fromRunnable {
                if (config.postLogger) {
                    logger.info("Logging POST Filter: response code -> {}", response.statusCode)
                }
            })
        }, Ordered.LOWEST_PRECEDENCE)
    }

    data class Config(
        val baseMessage: String,
        val preLogger: Boolean,
        val postLogger: Boolean
    ) {
    }

}