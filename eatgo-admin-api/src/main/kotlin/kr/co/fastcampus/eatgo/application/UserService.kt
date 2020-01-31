package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.User
import kr.co.fastcampus.eatgo.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class UserService(
        @Autowired
        private val userRepoistory: UserRepository
) {
    fun getUsers() = userRepoistory.findAll()
    fun addUser(email: String, name: String): User {
        val user = User(email = email, name = name, level = 1)
        return userRepoistory.save(user)
    }

    fun updateUser(id: Long, email: String, name: String, level: Long): User? {
        val user = userRepoistory.findById(id).orElse(null)

        user.email = email
        user.name = name
        user.level = level

        return user
    }

    fun deactivateUser(id: Long): User {
        val user = userRepoistory.findById(id).orElse(null)

        user.deactivate()

        return user
    }
}
