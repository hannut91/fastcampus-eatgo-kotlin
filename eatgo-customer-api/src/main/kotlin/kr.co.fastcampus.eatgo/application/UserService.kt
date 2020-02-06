package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.User
import kr.co.fastcampus.eatgo.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class UserService(
        @Autowired
        private val userRepository: UserRepository,

        @Autowired
        private val passwordEncoder: PasswordEncoder
) {

    fun registerUser(email: String, name: String, password: String): User {
        val existed = userRepository.findByEmail(email)
        if (existed != null) {
            throw EmailExistedException(email)
        }

        val encodedPassword = passwordEncoder.encode(password)
        val user = User(email = email, name = name, password = encodedPassword,
                level = 1)

        return userRepository.save(user)
    }

    fun authenticate(email: String, password: String): User {
        val user = userRepository.findByEmail(email)
                ?: throw EmailNotExistedException(email)

        if (passwordEncoder.matches(password, user.password).not()) {
            throw PasswordWrongException()
        }

        return user
    }
}
