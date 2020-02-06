package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import kr.co.fastcampus.eatgo.domain.User
import kr.co.fastcampus.eatgo.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.security.crypto.password.PasswordEncoder

internal class UserServiceTest {

    private lateinit var userService: UserService

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var passwordEncoder: PasswordEncoder

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        userService = UserService(userRepository, passwordEncoder)
    }

    @Test
    fun registerUser() {
        given(passwordEncoder.encode("test")).willReturn("test")

        val email = "tester@example.com"
        val name = "Tester"
        val password = "test"
        userService.registerUser(email, name, password)

        verify(userRepository).save(any())
    }

    @Test
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

    @Test
    fun authenticateWithValidAttributes() {
        val email = "tester@example.com"
        val password = "test"

        val mockUser = User(email = email, password = password)
        given(userRepository.findByEmail(email))
                .willReturn(mockUser)

        given(passwordEncoder.matches(any(), any())).willReturn(true)

        val user = userService.authenticate(email, password)

        assertThat(user.email).isEqualTo(email)
    }

    @Test
    fun authenticateWithNotExistedEmail() {
        val email = "x@example.com"
        val password = "test"

        given(userRepository.findByEmail(email)).willReturn(null)

        assertThrows<EmailNotExistedException> {
            userService.authenticate(email, password)
        }
    }

    @Test
    fun authenticateWithWrongPassword() {
        val email = "tester@example.com"
        val password = "x"

        val mockUser = User(email = email, password = password)
        given(userRepository.findByEmail(email)).willReturn(mockUser)

        given(passwordEncoder.matches(any(), any())).willReturn(false)

        assertThrows<PasswordWrongException> {
            userService.authenticate(email, password)
        }
    }
}
