package kr.co.fastcampus.eatgo.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotEmpty

@Entity
class User(
        @Id
        @GeneratedValue
        var id: Long = 0,

        @field:NotEmpty
        var email: String = "",

        @field:NotEmpty
        var name: String = "",

        var password: String = "",

        var level: Long = 0
) {
    val isAdmin: Boolean
        get() = level >= 100

    val isActive: Boolean
        get() = level > 0

    fun deactivate() {
        level = 0
    }

    @JsonIgnore
    fun getAccessToken() =
            if (password.isEmpty()) "" else password.substring(0, 10)
}
