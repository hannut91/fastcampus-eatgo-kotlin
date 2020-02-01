package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kr.co.fastcampus.eatgo.application.UserService
import kr.co.fastcampus.eatgo.domain.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UserController::class)
internal class UserControllerTest {
    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var userService: UserService

    @Test
    fun create() {
        val mockUser = User(id = 1004, email = "tester@example.com",
                name = "Tester", password = "test")
        given(userService.registerUser("tester@example.com", "Tester", "test"))
                .willReturn(mockUser)

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"tester@example.com\", " +
                        "\"name\": \"Tester\", \"password\": \"test\"}"))
                .andExpect(status().isCreated)
                .andExpect(header().string("location", "/users/1004"))

        verify(userService).registerUser(
                eq("tester@example.com"), eq("Tester"), eq("test"))
    }
}
