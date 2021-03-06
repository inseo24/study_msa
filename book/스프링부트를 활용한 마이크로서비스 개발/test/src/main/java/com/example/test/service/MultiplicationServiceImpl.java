package com.example.test.service;

import com.example.test.domain.Multiplication;
import com.example.test.domain.MultiplicationResultAttempt;
import com.example.test.domain.User;
import com.example.test.multiplication.EventDispatcher;
import com.example.test.multiplication.MultiplicationSolvedEvent;
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
    private EventDispatcher eventDispatcher;

    @Autowired
    public MultiplicationServiceImpl(com.example.test.service.RandomGeneratorService randomGeneratorService, UserRepository userRepository, MultiplicationResultAttemptRepository multiplicationResultAttemptRepository, EventDispatcher eventDispatcher) {
        this.randomGeneratorService = randomGeneratorService;
        this.userRepository = userRepository;
        this.multiplicationResultAttemptRepository = multiplicationResultAttemptRepository;
        this.eventDispatcher = eventDispatcher;
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

        Assert.isTrue(!resultAttempt.isCorrect(), "????????? ?????? ?????? ??? ????????????!");

        boolean correct = resultAttempt.getResultAttempt() ==
                resultAttempt.getMultiplication().getFactorA() *
                        resultAttempt.getMultiplication().getFactorB();

        Assert.isTrue(!resultAttempt.isCorrect(), "????????? ????????? ?????? ??? ????????????!");

        boolean isCorrect = resultAttempt.getResultAttempt() ==
                resultAttempt.getMultiplication().getFactorA() *
                        resultAttempt.getMultiplication().getFactorB();

        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
                resultAttempt.getUser(), resultAttempt.getMultiplication(),
                resultAttempt.getResultAttempt(), isCorrect);

        multiplicationResultAttemptRepository.save(checkedAttempt);

        eventDispatcher.send(new MultiplicationSolvedEvent(
                checkedAttempt.getId(),
                checkedAttempt.getUser().getId(),
                checkedAttempt.isCorrect()
        ));

        return isCorrect;
    }

    @Override
    public MultiplicationResultAttempt getResultById(Long resultId) {
        return multiplicationResultAttemptRepository.findById(resultId).orElseThrow();
    }
}
