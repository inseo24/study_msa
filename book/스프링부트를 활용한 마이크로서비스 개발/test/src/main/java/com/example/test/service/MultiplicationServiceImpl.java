package com.example.test.service;

import com.example.test.domain.Multiplication;
import com.example.test.domain.MultiplicationResultAttempt;
import com.example.test.domain.User;
import com.example.test.repository.MultiplicationResultAttemptRepository;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MultiplicationServiceImpl implements MultiplicationService{

    private RandomGeneratorService randomGeneratorService;
    private UserRepository userRepository;
    private MultiplicationResultAttemptRepository multiplicationResultAttemptRepository;

    @Autowired
    public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService, UserRepository userRepository, MultiplicationResultAttemptRepository multiplicationResultAttemptRepository) {
        this.randomGeneratorService = randomGeneratorService;
        this.userRepository = userRepository;
        this.multiplicationResultAttemptRepository = multiplicationResultAttemptRepository;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateFactor();
        int factorB = randomGeneratorService.generateFactor();
        return new Multiplication(factorA, factorB);
    }

    @Override
    public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
        return multiplicationResultAttemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
    }

    @Transactional
    @Override
    public boolean checkAttempt(final MultiplicationResultAttempt resultAttempt) {
        Optional<User> user = userRepository.findByAlias(resultAttempt.getUser().getAlias());

        Assert.isTrue(!resultAttempt.isCorrect(), "채점한 채로 보낼 수 없습니다!");

        boolean correct = resultAttempt.getResultAttempt() ==
                resultAttempt.getMultiplication().getFactorA() *
                        resultAttempt.getMultiplication().getFactorB();

        Assert.isTrue(!resultAttempt.isCorrect(), "채점한 상태로 보낼 수 없습니다!");

        boolean isCorrect = resultAttempt.getResultAttempt() ==
                resultAttempt.getMultiplication().getFactorA() *
                        resultAttempt.getMultiplication().getFactorB();

        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
                resultAttempt.getUser(), resultAttempt.getMultiplication(),
                resultAttempt.getResultAttempt(), isCorrect);

        multiplicationResultAttemptRepository.save(checkedAttempt);

        return isCorrect;
    }
}
