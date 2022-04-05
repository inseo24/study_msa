package com.example.discoverytest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableEurekaServer
@SpringBootApplication
class DiscoveryTestApplication

fun main(args: Array<String>) {
    runApplication<DiscoveryTestApplication>(*args)
}
