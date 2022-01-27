package com.example.test.service;

import com.example.test.domain.Multiplication;
import com.example.test.domain.MultiplicationResultAttempt;

public interface MultiplicationService {
    Multiplication createRandomMultiplication();

    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);
}
