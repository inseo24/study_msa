package com.example.test.service;

import com.example.test.domain.Multiplication;
import com.example.test.domain.MultiplicationResultAttempt;
import com.example.test.domain.User;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class MultiplicationServiceImplTest {

    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService);
    }

    @Test
    public void createRandomMultiplication(){
        // given
        given(randomGeneratorService.generateFactor()).willReturn(50, 30);

        Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();

        AssertionsForClassTypes.assertThat(multiplication.getFactorA()).isEqualTo(50);
        AssertionsForClassTypes.assertThat(multiplication.getFactorB()).isEqualTo(30);
//        AssertionsForClassTypes.assertThat(multiplication.getResult()).isEqualTo(1500);
    }

    @Test
    public void checkCorrectAttemptTest() {
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("John");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000);

        boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);

        assertThat(attemptResult).isTrue();
    }

    @Test
    public void checkWrongAttemptTest() {
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("John");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010);

        boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);

        assertThat(attemptResult).isFalse();
    }

}
