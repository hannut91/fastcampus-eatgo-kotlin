package kr.co.fastcampus.eatgo.domain

import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, Long> {
    override fun findById(id: Long): Optional<User>

    override fun findAll(): List<User>
    
    fun save(user: User): User
}
