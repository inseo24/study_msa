package com.example.kotlin.domain

/*
* 애플리케이션에 곱셈을 나타내는 클래스
* */
data class Multiplication(
    var factorA: Int = 0,
    var factorB: Int = 0,
    val result: Int = factorA * factorB
) {

}