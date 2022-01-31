package com.example.kotlin.service

import com.example.kotlin.domain.Multiplication
import com.example.kotlin.domain.MultiplicationResultAttempt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MultiplicationServiceImpl(
    @Autowired
    val randomGeneratorService: RandomGeneratorService
) : MultiplicationService {

    override fun createRandomMultiplication(): Multiplication {
        val factorA: Int = randomGeneratorService.generateRandomFactor()
        val factorB: Int = randomGeneratorService.generateRandomFactor()
        return Multiplication(factorA, factorB)
    }

    override fun checkAttempt(resultAttempt: MultiplicationResultAttempt): Boolean {
        return resultAttempt.resultAttempt ==
                resultAttempt.multiplication?.factorA!! * resultAttempt.multiplication?.factorB!!
    }
}