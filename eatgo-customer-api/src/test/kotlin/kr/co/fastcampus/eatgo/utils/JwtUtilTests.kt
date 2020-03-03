package kr.co.fastcampus.eatgo.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class JwtUtilTests {
    @Test
    fun createToken() {
        val jwtUtil = JwtUtil()
        val token = jwtUtil.createToken(1004L, "John")

        assertThat(token).contains(".")
    }
}