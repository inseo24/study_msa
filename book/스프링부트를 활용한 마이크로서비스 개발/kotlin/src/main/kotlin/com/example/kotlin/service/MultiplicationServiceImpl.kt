package com.example.kotlin.service

import com.example.kotlin.domain.Multiplication
import com.example.kotlin.domain.MultiplicationResultAttempt
import com.example.kotlin.domain.User
import com.example.kotlin.repository.MultiplicationResultAttemptRepository
import com.example.kotlin.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.util.*
import javax.transaction.Transactional

@Service
class MultiplicationServiceImpl(
    @Autowired val randomGeneratorService: RandomGeneratorService,
    @Autowired val attemptRepository: MultiplicationResultAttemptRepository,
    @Autowired val userRepository: UserRepository
) : MultiplicationService {

    override fun createRandomMultiplication(): Multiplication {
        val factorA: Int = randomGeneratorService.generateRandomFactor()
        val factorB: Int = randomGeneratorService.generateRandomFactor()
        return Multiplication(factorA, factorB)
    }

    @Transactional
    override fun checkAttempt(attempt: MultiplicationResultAttempt): Boolean {
        // 해당 닉네임의 사용자가 존재하는지 확인
        val user = userRepository.findByAlias(attempt.user?.alias!!)

        // 조작된 답안을 방지
        Assert.isTrue(!attempt.correct, "채점한 상태로 보낼 수 없습니다!!")

        // 답안을 채점
        val isCorrect: Boolean = attempt.resultAttempt ==
                attempt.multiplication?.factorA!! * attempt.multiplication?.factorB!!

        // 복사본을 만들고 correct 필드를 상황에 맞게 설정
        val checkedAttempt = MultiplicationResultAttempt(
            attempt.user, attempt.multiplication, attempt.resultAttempt, isCorrect)

        // 답안을 저장
        attemptRepository.save(checkedAttempt)

        // 결과를 반환
        return isCorrect
    }

    override fun getStatsForUser(userAlias: String): List<MultiplicationResultAttempt> {
        return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias)
    }
}