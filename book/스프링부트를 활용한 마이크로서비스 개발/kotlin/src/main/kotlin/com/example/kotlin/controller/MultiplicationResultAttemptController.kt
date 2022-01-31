package com.example.kotlin.controller

import com.example.kotlin.domain.MultiplicationResultAttempt
import com.example.kotlin.service.MultiplicationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/results")
class MultiplicationResultAttemptController(
    @Autowired
    val multiplicationService: MultiplicationService
) {

    @PostMapping
    fun postResult(@RequestBody multiplicationResultAttempt: MultiplicationResultAttempt): ResponseEntity<MultiplicationResultAttempt> {
        val isCorrect = multiplicationService.checkAttempt(multiplicationResultAttempt)
        val attemptCopy = MultiplicationResultAttempt(
            multiplicationResultAttempt.user, multiplicationResultAttempt.multiplication,
            multiplicationResultAttempt.resultAttempt, isCorrect
        )
        return ResponseEntity.ok(attemptCopy)
    }

    @GetMapping
    fun getStatistics(@RequestParam("alias") alias: String): ResponseEntity<List<MultiplicationResultAttempt>>{
        return ResponseEntity.ok(multiplicationService.getStatsForUser(alias))
    }
}