package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kr.co.fastcampus.eatgo.domain.User
import kr.co.fastcampus.eatgo.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*

internal class UserServiceTest {
    private lateinit var userService: UserService

    @Mock
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)

        userService = UserService(userRepository)
    }

    @Test
    fun getUsers() {
        val mockUsers = arrayListOf<User>()
        mockUsers.add(
                User(email = "tester@example.com", name = "테스터", level = 1)
        )
        given(userRepository.findAll()).willReturn(mockUsers)

        val users = userService.getUsers()

        assertThat(users.first().name).isEqualTo("테스터")
    }

    @Test
    fun addUser() {
        val email = "admin@exapmle.com"
        val name = "Administrator"
        val mockUser = User(email = email, name = name)
        given(userRepository.save(any())).willReturn(mockUser)

        val user = userService.addUser(email, name)

        assertThat(user.name).isEqualTo(name)
    }

    @Test
    fun updateUser() {
        val id = 1004L
        val email = "admin@exapmle.com"
        val name = "Superman"
        val level = 100L
        val mockUser = User(id = id, email = email, name = "Administrator",
                level = 1)
        given(userRepository.findById(id)).willReturn(Optional.of(mockUser))

        val user = userService.updateUser(id, email, name, level)

        verify(userRepository).findById(eq(id))

        assertThat(user?.name).isEqualTo("Superman")
        assertThat(user?.isAdmin).isEqualTo(true)
    }

    @Test
    fun deactivatedUser() {
        val id = 1004L
        val mockUser = User(id = id, email = "admin@exapmle.com",
                name = "Administrator", level = 100L)
        given(userRepository.findById(id)).willReturn(Optional.of(mockUser))

        val user = userService.deactivateUser(1004)

        verify(userRepository).findById(1004)

        assertThat(user.isAdmin).isEqualTo(false)
        assertThat(user.isActive).isEqualTo(false)
    }
}
