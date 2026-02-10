package org.dev1.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class HelloController {
    @GetMapping("/hello")
    fun say_hello():String{
        return "Hello"
    }



}