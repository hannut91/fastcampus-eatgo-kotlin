package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kr.co.fastcampus.eatgo.application.EmailNotExistedException
import kr.co.fastcampus.eatgo.application.PasswordWrongException
import kr.co.fastcampus.eatgo.application.UserService
import kr.co.fastcampus.eatgo.domain.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(SessionController::class)
internal class SessionControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var userService: UserService

    @Test
    fun createWithValidAttributes() {
        val email = "tester@example.com"
        val password = "test"

        val mockUser = User(email = email, password = "ACCESS_TOKEN")
        given(userService.authenticate(email, password)).willReturn(mockUser)

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"tester@example.com\", " +
                        "\"password\": \"test\"}"))
                .andExpect(status().isCreated)
                .andExpect(header().string("location", "/session"))
                .andExpect(content()
                        .string("{\"accessToken\":\"ACCESS_TOK\"}"))

        verify(userService).authenticate(eq(email), eq(password))
    }

    @Test
    fun createWithNotExistedEmail() {
        given(userService.authenticate("x@example.com", "test"))
                .willThrow(EmailNotExistedException("x@example.com"))

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"x@example.com\", " +
                        "\"password\": \"test\"}"))
                .andExpect(status().isBadRequest)

        verify(userService).authenticate(eq("x@example.com"), eq("test"))
    }

    @Test
    fun createWithWrongPassword() {
        given(userService.authenticate("tester@example.com", "x"))
                .willThrow(PasswordWrongException())

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"tester@example.com\", " +
                        "\"password\": \"x\"}"))
                .andExpect(status().isBadRequest)

        verify(userService).authenticate(eq("tester@example.com"), eq("x"))
    }
}
