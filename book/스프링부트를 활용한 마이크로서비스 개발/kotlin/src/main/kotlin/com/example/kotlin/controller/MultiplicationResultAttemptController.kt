package com.example.kotlin.controller

import com.example.kotlin.domain.MultiplicationResultAttempt
import com.example.kotlin.service.MultiplicationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/results")
class MultiplicationResultAttemptController(
    @Autowired
    val multiplicationService: MultiplicationService
) {
    class ResultResponse(correct: Boolean) {
        var correct: Boolean = true
    }

    @PostMapping
    fun postResult(@RequestBody multiplicationResultAttempt: MultiplicationResultAttempt): ResponseEntity<ResultResponse> {
        return ResponseEntity.ok(ResultResponse(multiplicationService.checkAttempt(multiplicationResultAttempt)))
    }
}