package com.example.kotlin.domain

/*
* 애플리케이션에 곱셈을 나타내는 클래스
* */
class Multiplication(
    private var factorA: Int,
    private var factorB: Int,
    private var result: Int = factorA * factorB
) {
    override fun toString(): String {
        return "Multiplication(factorA=$factorA, factorB=$factorB, result(A*B)=$result)"
    }
}