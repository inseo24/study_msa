package com.example.test.service;

import com.example.test.domain.Multiplication;
import com.example.test.domain.MultiplicationResultAttempt;
import com.example.test.domain.User;
import com.example.test.repository.MultiplicationResultAttemptRepository;
import com.example.test.repository.UserRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class MultiplicationServiceImplTest {

    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Mock
    private MultiplicationResultAttemptRepository attemptRepository;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService
        , userRepository, attemptRepository);
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
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user,
                multiplication, 3000, false);
        MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user,
                multiplication, 3000, true);
        given(userRepository.findByAlias("John")).willReturn(Optional.empty());

        boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);

        assertThat(attemptResult).isTrue();
        verify(attemptRepository).save(verifiedAttempt);
    }

    @Test
    public void checkWrongAttemptTest() {
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("John");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user,
                multiplication, 3010, false);
        given(userRepository.findByAlias("John")).willReturn(Optional.empty());

        boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);

        assertThat(attemptResult).isFalse();
        verify(attemptRepository).save(attempt);
    }

    @Test
    public void retrieveStatsTest() {
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("John");
        MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(user,
                multiplication, 3010, false);
        MultiplicationResultAttempt attempt2 = new MultiplicationResultAttempt(user,
                multiplication, 3051, false);
        List<MultiplicationResultAttempt> latestAttempts = Lists.newArrayList(attempt1, attempt2);
        given(userRepository.findByAlias("John")).willReturn(Optional.empty());
        given(attemptRepository.findTop5ByUserAliasOrderByIdDesc("John")).willReturn(latestAttempts);

        List<MultiplicationResultAttempt> latestAttemptsResult =
                multiplicationServiceImpl.getStatsForUser("John");

        assertThat(latestAttemptsResult).isEqualTo(latestAttempts);

    }

}
