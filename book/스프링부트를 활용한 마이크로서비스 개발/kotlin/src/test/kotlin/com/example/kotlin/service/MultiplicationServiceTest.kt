package com.example.kotlin.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class MultiplicationServiceTest {

    @MockBean
    lateinit var randomGeneratorService: RandomGeneratorService

    @Autowired
    lateinit var multiplicationService: MultiplicationService

    @Test
    fun createRandomMultiplicationTest() {
        // given (randomGeneratorService 가 처음에는 50, 나중에는 30을 반환하게 설정)
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30)

        // when
        val multiplication = multiplicationService.createRandomMultiplication()

        // assert
        assertThat(multiplication.factorA).isEqualTo(50)
        assertThat(multiplication.factorB).isEqualTo(30)
        assertThat(multiplication.result).isEqualTo(1500)
    }

}


