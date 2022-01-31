package com.example.kotlin.service

import org.springframework.stereotype.Service
import java.util.*

@Service
class RandomGeneratorServiceImpl(
    val MINIMUM_FACTOR: Int = 11,
    val MAXIMUM_FACTOR: Int = 99
) : RandomGeneratorService{

    override fun generateRandomFactor(): Int {
        return Random().nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1) + MINIMUM_FACTOR
    }
}