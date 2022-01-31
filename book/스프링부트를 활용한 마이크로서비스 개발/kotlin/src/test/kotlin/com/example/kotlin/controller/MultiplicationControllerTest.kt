package com.example.kotlin.controller

import com.example.kotlin.domain.Multiplication
import com.example.kotlin.service.MultiplicationService
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

@WebMvcTest(MultiplicationController::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MultiplicationControllerTest {

    @MockBean
    lateinit var multiplicationService: MultiplicationService

    @Autowired
    lateinit var mockMvc: MockMvc

    // initFields() 메서드를 통해 자동 초기화
    lateinit var json: JacksonTester<Multiplication>

    @BeforeAll
    fun setup() {
        JacksonTester.initFields(this, ObjectMapper())
    }

    @Test
    fun getRandomMultiplicationTest() {
        // given
        given(multiplicationService.createRandomMultiplication()).willReturn(Multiplication(70, 20))

        // when
        val response : MockHttpServletResponse = mockMvc.perform(
            get("/multiplications/random")
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn().response

        // then
        assertThat(response.status).isEqualTo(HttpStatus.OK.value())
        assertThat(response.contentAsString)
            .isEqualTo(json.write(Multiplication(70, 20)).json)
    }
}