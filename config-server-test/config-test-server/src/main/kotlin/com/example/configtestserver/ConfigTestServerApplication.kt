package com.example.configtestserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class ConfigTestServerApplication

fun main(args: Array<String>) {
    runApplication<ConfigTestServerApplication>(*args)
}
