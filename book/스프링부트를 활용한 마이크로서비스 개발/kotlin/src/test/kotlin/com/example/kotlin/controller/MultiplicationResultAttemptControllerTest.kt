package com.example.kotlin.controller

import com.example.kotlin.domain.Multiplication
import com.example.kotlin.domain.MultiplicationResultAttempt
import com.example.kotlin.service.MultiplicationService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import com.example.kotlin.domain.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

@WebMvcTest(MultiplicationResultAttemptController::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MultiplicationResultAttemptControllerTest {

    @MockBean
    lateinit var multiplicationService: MultiplicationService

    @Autowired
    lateinit var mockMvc: MockMvc

    // any() 함수에서 null 리턴 -> kotlin class 는 final 이라 mocking 이 불가능
    // mockito-kotlin 라이브러리도 있다 하던데 일단 여기서는 any()를 재정의해 사용
    private fun <T> any(java: Class<MultiplicationResultAttempt>): T {
        Mockito.any<T>()
        return null as T
    }

    lateinit var jsonResultAttempt: JacksonTester<MultiplicationResultAttempt>
    lateinit var jsonResultAttemptList: JacksonTester<List<MultiplicationResultAttempt>>

    @BeforeAll
    fun setup() {
        JacksonTester.initFields(this, ObjectMapper())
    }

    @Test
    fun postResultReturnCorrect() {
        genericParameterizedTest(true)
    }

    @Test
    fun postResultReturnNotCorrect() {
        genericParameterizedTest(false)
    }

    private fun genericParameterizedTest(correct: Boolean) {
        // given
        given(multiplicationService.checkAttempt(any(MultiplicationResultAttempt::class.java)))
            .willReturn(correct)

        val user = User("seoin")
        val multiplication = Multiplication(50, 70)
        val attempt = MultiplicationResultAttempt(user, multiplication, 3500, correct)

        // when
        val response : MockHttpServletResponse = mockMvc.perform(
            post("/results")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonResultAttempt.write(attempt).json)
        ).andReturn().response

        // then
        assertThat(response.status).isEqualTo(HttpStatus.OK.value())
        assertThat(response.contentAsString)
            .isEqualTo(jsonResultAttempt.write(MultiplicationResultAttempt(
                attempt.user,
                attempt.multiplication,
                attempt.resultAttempt,
                correct)).json)
    }

    @Test
    fun getUserStatus() {
        // given
        val user = User("seoin")
        val multiplication = Multiplication(50, 70)
        val attempt = MultiplicationResultAttempt(user, multiplication, 3500, true)
        val recentAttempts = listOf(attempt, attempt)
        given(multiplicationService.getStatsForUser("seoin")).willReturn(recentAttempts)

        // when
        val response : MockHttpServletResponse = mockMvc.perform(
            get("/results")
                .param("alias", "seoin")
        ).andReturn().response

        // then
        assertThat(response.status).isEqualTo(HttpStatus.OK.value())
        assertThat(response.contentAsString).isEqualTo(jsonResultAttemptList.write(recentAttempts).json)
    }
}