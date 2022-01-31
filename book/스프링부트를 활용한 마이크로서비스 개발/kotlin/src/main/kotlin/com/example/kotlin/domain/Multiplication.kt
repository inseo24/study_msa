package com.example.kotlin.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/*
* 애플리케이션에 곱셈을 나타내는 클래스
* */
@Entity
class Multiplication(

    var factorA: Int = 0,
    var factorB: Int = 0,
    val result: Int = factorA * factorB
) {
    @Id
    @GeneratedValue
    @Column(name = "MULTIPLICATION_ID")
    val id: Long? = null
}