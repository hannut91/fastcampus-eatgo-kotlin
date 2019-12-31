package kr.co.fastcampus.eatgo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EatgoCustomerApiApplication

fun main(args: Array<String>) {
    runApplication<EatgoCustomerApiApplication>(*args)
}
