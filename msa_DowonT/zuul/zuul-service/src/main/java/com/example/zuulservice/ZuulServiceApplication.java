package com.example.zuulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 최신 eclipse와 intellij에서 이전 버전(2.4.x 하위 버전)의 spring boot를 선택할 수 없음 -> zuul도 못 씀
// 그냥 없이 진행함

// @EnableZuulProxy
@SpringBootApplication
public class ZuulServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServiceApplication.class, args);
    }

}
