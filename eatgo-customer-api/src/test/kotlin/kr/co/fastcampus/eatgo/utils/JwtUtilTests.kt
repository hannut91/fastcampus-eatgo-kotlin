package kr.co.fastcampus.eatgo.utils

import io.jsonwebtoken.Claims
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class JwtUtilTests {

    private lateinit var jwtUtil: JwtUtil

    @BeforeEach
    fun setUp() {
        jwtUtil = JwtUtil()
    }

    @Test
    fun createToken() {
        val token = jwtUtil.createToken(1004L, "John")

        assertThat(token).contains(".")
    }

    @Test
    fun getClaims() {
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.8hm6ZOJykSINHxL-rf0yV882fApL3hyQ9-WGlJUyo2A"
        val claims: Claims = jwtUtil.getClaims(token)

        assertThat(claims["userId"]).isEqualTo(1004)
        assertThat(claims["name"]).isEqualTo("John")
    }
}
