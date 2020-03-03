package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.UserService
import kr.co.fastcampus.eatgo.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class SessionController {

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/session")
    fun create(
            @RequestBody resource: SessionRequestDto
    ): ResponseEntity<SessionResponseDto> {
        val email = resource.email
        val password = resource.password

        val user = userService.authenticate(email, password)

        val accessToken = jwtUtil.createToken(user.id, user.name)

        val url = "/session"
        return ResponseEntity.created(URI(url))
                .body(SessionResponseDto(accessToken = accessToken))
    }
}
