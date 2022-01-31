package com.example.kotlin.repository

import com.example.kotlin.domain.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, Long> {
    fun findByAlias(alias: String) : User?
}