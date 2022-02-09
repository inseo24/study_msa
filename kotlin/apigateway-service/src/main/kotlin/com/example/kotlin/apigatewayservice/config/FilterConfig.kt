package com.example.kotlin.apigatewayservice.config

import mu.KLogging
import mu.KotlinLogging
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
class FilterConfig {
    companion object: KLogging()

    fun hi() {
        logger.info { "this is info log, too" }
    }

    private val logger = KotlinLogging.logger {}

    fun a() {
        logger.info { "this is info log" }
    }

//
//    @Bean
//    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
//        return builder.routes {
//            route {
//                this.path("/first-service/**")
//                this.filters {
//                    this.addRequestHeader("first-request", "first-request-header")
//                        .addResponseHeader("first-response", "first-response-header")
//                        .uri("http://localhost:8081")}
//            }
//            route {
//                this.path("/second-service/**")
//                this.filters {
//                    this.addRequestHeader("second-request", "second-request-header")
//                        .addResponseHeader("second-response", "second-response-header")
//                        .uri("http://localhost:8082")}
//            }
//        }
//    }
}