package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.UserService
import kr.co.fastcampus.eatgo.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/users")
    fun list() = userService.getUsers()

    @PostMapping("/users")
    fun create(
            @RequestBody resource: User
    ): ResponseEntity<Any> {
        val email = resource.email
        val name = resource.name
        val user = userService.addUser(email, name)

        val url = "/users/${user.id}"
        return ResponseEntity.created(URI(url)).body("{}")
    }

    @PatchMapping("/users/{id}")
    fun update(
            @PathVariable("id") id: Long,
            @RequestBody resource: User
    ): String {
        val email = resource.email
        val name = resource.name
        val level = resource.level

        userService.updateUser(id, email, name, level)

        return "{}"
    }

    @DeleteMapping("/users/{id}")
    fun delete(@PathVariable id: Long): String {
        userService.deactivateUser(id)

        return "{}"
    }
}
