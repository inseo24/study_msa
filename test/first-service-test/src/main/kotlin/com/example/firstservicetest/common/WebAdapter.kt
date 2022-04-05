package com.example.firstservicetest.common

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ResponseBody

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Controller
@ResponseBody
annotation class WebAdapter(

    @get:AliasFor(annotation = Controller::class)
    val value: String = ""
)