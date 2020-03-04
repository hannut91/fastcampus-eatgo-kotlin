package kr.co.fastcampus.eatgo.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.security.Key

@Component
class JwtUtil {

    private val secret = "12345678901234567890123456789012"
    private val key: Key

    init {
        key = Keys.hmacShaKeyFor(secret.toByteArray())
    }

    fun createToken(userId: Long, name: String): String =
            Jwts.builder()
                    .claim("userId", userId)
                    .claim("name", name)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact()

    fun getClaims(token: String): Claims =
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .body
}
