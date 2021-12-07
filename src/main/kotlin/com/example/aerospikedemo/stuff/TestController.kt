package com.example.aerospikedemo.stuff

import com.example.aerospikedemo.model.HelloWorld
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/")
@RestController
class TestController {

    @Cacheable(cacheNames = ["test"])
    @RequestMapping("/test")
    fun test(): HelloWorld {
        return HelloWorld("Hello world!");
    }
}
