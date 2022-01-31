package com.example.kotlin.service

import org.springframework.stereotype.Service

@Service
class RandomGeneratorServiceImpl : RandomGeneratorService{
    override fun generateRandomFactor(): Int {
        return 0
    }
}