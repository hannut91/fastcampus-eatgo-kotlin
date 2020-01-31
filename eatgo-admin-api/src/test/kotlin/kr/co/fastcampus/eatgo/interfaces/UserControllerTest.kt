package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kr.co.fastcampus.eatgo.application.UserService
import kr.co.fastcampus.eatgo.domain.User
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UserController::class)
internal class UserControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @Test
    fun list() {
        val users = arrayListOf<User>()
        users.add(User(email = "tester@example.com", name = "테스터", level = 1))

        given(userService.getUsers()).willReturn(users)

        mvc.perform(get("/users"))
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("테스터")))
    }

    @Test
    fun create() {
        val email = "admin@exapmle.com"
        val name = "Administrator"
        val user = User(email = email, name = name)
        given(userService.addUser(email, name)).willReturn(user)

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"admin@exapmle.com\", " +
                        "\"name\": \"Administrator\"}"))
                .andExpect(status().isCreated)

        verify(userService).addUser(email, name)
    }

    @Test
    fun update() {
        mvc.perform(patch("/users/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"admin@exapmle.com\", " +
                        "\"name\": \"Administrator\", \"level\": 100}"))
                .andExpect(status().isOk)

        val id = 1004L
        val email = "admin@exapmle.com"
        val name = "Administrator"
        val level = 100L
        verify(userService).updateUser(eq(id), eq(email), eq(name), eq(level))
    }

    @Test
    fun decativate() {
        mvc.perform(delete("/users/1004"))
                .andExpect(status().isOk)

        verify(userService).deactivateUser(1004L)
    }
}
