package com.kotlin.secondservice

import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/second-service")
class Controller(
    val environment: Environment
) {

    @GetMapping("/welcome")
    fun welcome() = "Welcome to the Second service!"

    @GetMapping("/message")
    fun message(@RequestHeader("second-request") header: String) = "Hello world in Second service"


    @GetMapping("/check")
    fun check() = "Hi, there. This is message from Second Service"
}