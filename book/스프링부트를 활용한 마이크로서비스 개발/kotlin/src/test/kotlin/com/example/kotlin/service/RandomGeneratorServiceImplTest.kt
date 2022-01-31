package com.example.kotlin.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RandomGeneratorServiceImplTest{

    private val randomGeneratorServiceImpl = RandomGeneratorServiceImpl()

    @Test
    fun generateRandomFactorIsBetweenExpectedLimits() {
        // 무작위 숫자 생성
        val randomFactors: List<Int> = (0 until 1000)
            .map { randomGeneratorServiceImpl.generateRandomFactor() }
            .toList()

        // 적당히 어려운 계산을 만들기 위해
        // 생성한 인수가 11~99 범위에 있는지 확인
        val range : List<Int> = (11 until 100).toList()
        Assertions.assertThat(randomFactors).containsOnlyElementsOf(range)
    }

}