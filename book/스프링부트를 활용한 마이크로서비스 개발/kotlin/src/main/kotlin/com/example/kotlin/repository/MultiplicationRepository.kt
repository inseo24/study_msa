package com.example.kotlin.repository

import com.example.kotlin.domain.Multiplication
import org.springframework.data.repository.CrudRepository

interface MultiplicationRepository : CrudRepository<Multiplication, Long> {
}