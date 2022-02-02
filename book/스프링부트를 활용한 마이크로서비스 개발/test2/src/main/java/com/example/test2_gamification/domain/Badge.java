package com.example.test2_gamification.domain;
/*
* 사용자가 획득할 수 있는 여러 종류의 배지 열거
* */
public enum Badge {

    // 점수로 획득하는 배지
    BRONZE_MULTIPLICATION,
    SILVER_MULTIPLICATION,
    GOLD_MULTIPLICATION,

    // 특정 조건으로 획득하는 배지
    FIRST_ATTEMPT,
    FIRST_WON,
    LUCKY_NUMBER

}
