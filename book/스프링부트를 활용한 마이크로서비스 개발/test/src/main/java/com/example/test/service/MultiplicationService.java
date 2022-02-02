package com.example.test.service;

import com.example.test.domain.Multiplication;
import com.example.test.domain.MultiplicationResultAttempt;

import java.util.List;

public interface MultiplicationService {
    Multiplication createRandomMultiplication();

    List<MultiplicationResultAttempt> getStatsForUser(String userAlias);

    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);

    MultiplicationResultAttempt getResultById(Long resultId);
}
