package com.example.kotlin.domain

import javax.persistence.*

@Entity
class MultiplicationResultAttempt(

    @ManyToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "USER_ID")
    var user: User? = null,

    @ManyToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "MULTIPLICATION_ID")
    var multiplication: Multiplication? = null,

    var resultAttempt: Int = -1,

    var correct: Boolean = false
) {
    @Id
    @GeneratedValue
    val id: Long? = null
}