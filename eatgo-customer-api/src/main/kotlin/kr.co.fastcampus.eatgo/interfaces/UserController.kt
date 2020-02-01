package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.UserService
import kr.co.fastcampus.eatgo.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/users")
    fun create(
            @RequestBody resource: User
    ): ResponseEntity<String> {
        val email = resource.email
        val name = resource.name
        val password = resource.password
        val user = userService.registerUser(email, name, password)
        val url = "/users/${user.id}"
        return ResponseEntity.created(URI(url)).body("{}")
    }
}
