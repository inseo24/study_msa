package com.example.firstservicetest.adapter.inbound.web

import com.example.firstservicetest.common.WebAdapter
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping

@WebAdapter
class ConfigTestResource {

    @Value("\${hi.data}")
    lateinit var hiData : String

    @GetMapping("/test/yaml")
    fun printYamlData(): String {
        println(hiData)
        return hiData
    }
}