package com.example.kotlin.apigatewayservice.filter

import mu.KLogging
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class GlobalFilter() : AbstractGatewayFilterFactory<GlobalFilter.Config>(Config::class.java) {

    companion object : KLogging()

    override fun apply(config: Config): GatewayFilter {
        // Global Pre Filter
        return GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            val request = exchange.request
            val response = exchange.response

            logger.info("Global filter baseMessage: {}", config.baseMessage)

            if (config.preLogger) {
                logger.info("Global Filter Start : request id -> {}", request.id)
            }
            chain.filter(exchange).then(Mono.fromRunnable {
                if (config.postLogger) {
                    logger.info(
                        "Global filter End: response code -> {}",
                        response.statusCode
                    )
                }
            })
        }
    }

    data class Config(
        val baseMessage: String,
        val preLogger: Boolean,
        val postLogger: Boolean
    ) {
    }

}