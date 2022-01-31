package com.example.kotlin.service

import com.example.kotlin.domain.Multiplication
import com.example.kotlin.domain.MultiplicationResultAttempt
import com.example.kotlin.domain.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MultiplicationServiceImplTest {

    private lateinit var multiplicationServiceImpl : MultiplicationServiceImpl

    @Mock
    lateinit var randomGeneratorService : RandomGeneratorService

    // @Before -> JUnit4
    // @BeforeAll -> JUnit5 -> 근데 이거 쓰려면 자바에서도 static 설정을 해줘야 한다는데
    // 코틀린에서는 위에 @TestInstance(Lifecycle.PER_CLASS) 안하면 에러 뜬다.
    // 이렇게 해두면 static 메서드가 아닌 곳에서도 사용 가능하다고.(테스트 클래스 당 인스턴스를 생성해서)
    @BeforeAll
    fun setup() {
        MockitoAnnotations.initMocks(this)
        multiplicationServiceImpl = MultiplicationServiceImpl(randomGeneratorService)
    }

    @Test
    fun createRandomMultiplicationTest() {
        // given (randomGeneratorService 가 처음에는 50, 나중에는 30을 반환하게 설정)
        BDDMockito.given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30)

        // when
        val multiplication = multiplicationServiceImpl.createRandomMultiplication()

        // assert
        assertThat(multiplication.factorA).isEqualTo(50)
        assertThat(multiplication.factorB).isEqualTo(30)
        assertThat(multiplication.result).isEqualTo(1500)
    }

    @Test
    fun checkCorrectAttemptTest() {
        // given
        val multiplication = Multiplication(50, 60)
        val user = User("seoin")
        val attempt = MultiplicationResultAttempt(user, multiplication, 3000)

        // when
        val attemptResult = multiplicationServiceImpl.checkAttempt(attempt)

        // assert
        assertThat(attemptResult).isTrue
    }

    @Test
    fun checkWrongAttemptTest() {
        // given
        val multiplication = Multiplication(50, 60)
        val user = User("seoin")
        val attempt = MultiplicationResultAttempt(user, multiplication, 3010)

        // when
        val attemptResult = multiplicationServiceImpl.checkAttempt(attempt)

        // assert
        assertThat(attemptResult).isFalse
    }
}