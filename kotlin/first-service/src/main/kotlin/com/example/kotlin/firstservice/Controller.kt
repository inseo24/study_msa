package com.example.kotlin.firstservice

import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/first-service")
class Controller(
    val environment: Environment
) {

    @GetMapping("/welcome")
    fun welcome() = "Welcome to the First service!"

    @GetMapping("/message")
    fun message(@RequestHeader("first-request") header: String) = "Hello world in First service"

    @GetMapping("/check")
    fun check() = "Hi, there. This is message from First Service"
}