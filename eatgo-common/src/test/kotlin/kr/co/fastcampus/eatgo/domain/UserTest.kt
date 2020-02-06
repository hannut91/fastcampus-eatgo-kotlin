package kr.co.fastcampus.eatgo.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserTests {
    @Test
    fun creation() {
        val user = User(email = "tester@example.com", name = "테스터",
                level = 100)

        assertThat(user.name).isEqualTo("테스터")
        assertThat(user.isAdmin).isEqualTo(true)
        assertThat(user.isActive).isEqualTo(true)

        user.deactivate()

        assertThat(user.isActive).isEqualTo(false)
    }

    @Test
    fun accessTokenWithPassword() {
        val user = User(password = "ACCESS_TOKEN")

        assertThat(user.getAccessToken()).isEqualTo("ACCESS_TOK")
    }

    @Test
    fun accessTokenWithoutPassword() {
        val user = User()

        assertThat(user.getAccessToken()).isEqualTo("")
    }
}
