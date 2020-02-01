package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.User
import kr.co.fastcampus.eatgo.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class UserService(@Autowired private val userRepository: UserRepository) {

    fun registerUser(email: String, name: String, password: String): User {
        val existed = userRepository.findByEmail(email)
        if (existed != null) {
            throw EmailExistedException(email)
        }

        val passwordEncoder = BCryptPasswordEncoder()
        val encodedPassword = passwordEncoder.encode(password)
        val user = User(email = email, name = name, password = encodedPassword,
                level = 1)

        return userRepository.save(user)
    }
}
