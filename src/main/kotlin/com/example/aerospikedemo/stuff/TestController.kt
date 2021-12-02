package com.example.aerospikedemo.stuff

import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.EnableCaching
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/")
@RestController
@EnableCaching
class TestController {

    @Cacheable(cacheNames = ["test"], sync = true)
    @RequestMapping("/test")
    fun test(): String {
        return "Hello world!"
    }

}
