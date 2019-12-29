package kr.co.fastcampus.eatgo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EatgoAdminApiApplication

fun main(args: Array<String>) {
    runApplication<EatgoAdminApiApplication>(*args)
}
