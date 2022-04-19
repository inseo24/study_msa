package com.example.firstservicetest

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class FirstServiceTestApplication

fun main(args: Array<String>) {
    runApplication<FirstServiceTestApplication>(*args)
}
