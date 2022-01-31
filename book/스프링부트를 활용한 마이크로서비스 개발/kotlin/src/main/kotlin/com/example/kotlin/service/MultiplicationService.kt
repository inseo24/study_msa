package com.example.kotlin.service

import com.example.kotlin.domain.Multiplication

/*
* 두 개의 무작위 인수를 인수를 담은 {@link Multiplication} 객체를 생성한다.
* 무작위로 생성되는 숫자 범위는 11~99.
*
* @return 무작위 인수를 담은 {@link Multiplication} 객수
* */
interface MultiplicationService {
    fun createRandomMultiplication() : Multiplication
}