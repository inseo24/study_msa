package com.example.kotlin.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
    var alias: String? = null
) {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    val id: Long? = null

}