package com.example.kotlin.msa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class MsaApplication

fun main(args: Array<String>) {
	runApplication<MsaApplication>(*args)
}
