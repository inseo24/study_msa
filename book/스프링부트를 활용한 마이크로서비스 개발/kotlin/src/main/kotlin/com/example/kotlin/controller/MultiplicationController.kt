package com.example.kotlin.controller

import com.example.kotlin.domain.Multiplication
import com.example.kotlin.service.MultiplicationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/multiplications")
class MultiplicationController(
    @Autowired
    val multiplicationService: MultiplicationService
) {

    @GetMapping("/random")
    fun getRandomMultiplication() : Multiplication {
        return multiplicationService.createRandomMultiplication()
    }
}