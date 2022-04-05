package com.example.gatewaytest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GatewayTestApplication

fun main(args: Array<String>) {
    runApplication<GatewayTestApplication>(*args)
}
