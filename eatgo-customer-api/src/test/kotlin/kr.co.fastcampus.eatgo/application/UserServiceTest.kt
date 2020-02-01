package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import kr.co.fastcampus.eatgo.domain.User
import kr.co.fastcampus.eatgo.domain.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class UserServiceTest {
    private lateinit var userService: UserService

    @Mock
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        userService = UserService(userRepository)
    }

    @Test
    fun registerUser() {
        val email = "tester@example.com"
        val name = "Tester"
        val password = "test"

        userService.registerUser(email, name, password)

        verify(userRepository).save(any())
    }

    @Test()
    fun registerUserWithExistedEmail() {
        val email = "tester@example.com"
        val name = "Tester"
        val password = "test"

        val mockUser = User(email = email, name = name, password = password)
        given(userRepository.findByEmail(email))
                .willReturn(mockUser)

        assertThrows<EmailExistedException> {
            userService.registerUser(email, name, password)
        }

        verify(userRepository, never()).save(any())
    }
}
