package com.example.firstservicetest.adapter.inbound.web

import com.example.firstservicetest.adapter.inbound.web.dto.CreatePostRequest
import com.example.firstservicetest.common.WebAdapter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@WebAdapter
class FirstServiceResource {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hi"
    }

    @PostMapping("/post")
    fun post(@RequestBody createPostRequest: CreatePostRequest): String {
        return createPostRequest.post
    }

    // 얘는 되고
    @GetMapping("/this/test")
    fun test() : Int {
        println("predicates *")
        return 0
    }

    // 얘는 실패해야 함(discovery 에 메소드 지정한 상태, 지정 안하면 성공함)
    @PostMapping("/this/test/post")
    fun postTest() : String {
        println("** predicates *")
        return "success"
    }
}