package kr.co.fastcampus.eatgo.interfaces

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController {
    @GetMapping("/")
    fun hello() = "Hello, world!!!"
}
