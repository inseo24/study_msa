package com.example.kotlin.domain

data class MultiplicationResultAttempt(
    var user: User? = null,
    var multiplication: Multiplication? = null,
    var resultAttempt: Int = -1
) {
}