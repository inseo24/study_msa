package com.example.kotlin.repository

import com.example.kotlin.domain.MultiplicationResultAttempt
import org.springframework.data.repository.CrudRepository

interface MultiplicationResultAttemptRepository
    : CrudRepository<MultiplicationResultAttempt, Long> {

        /*
        * @return 닉네임에 해당하는 사용자의 최근 답안 5개
        * */
        fun findTop5ByUserAliasOrderByIdDesc(userAlias: String): List<MultiplicationResultAttempt>
}