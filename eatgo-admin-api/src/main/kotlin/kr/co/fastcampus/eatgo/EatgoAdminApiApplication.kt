package kr.co.fastcampus.eatgo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.filter.CharacterEncodingFilter

@SpringBootApplication
class EatgoAdminApiApplication {
    @Bean
    fun characterEncodingFilter() = CharacterEncodingFilter().apply {
        encoding = "UTF-8"
        setForceEncoding(true)
    }
}

fun main(args: Array<String>) {
    runApplication<EatgoAdminApiApplication>(*args)
}
