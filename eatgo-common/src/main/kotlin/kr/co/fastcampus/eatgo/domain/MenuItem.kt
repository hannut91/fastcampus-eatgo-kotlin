package kr.co.fastcampus.eatgo.domain

import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class MenuItem(
        @Id
        @GeneratedValue
        var id: Long = 0,

        var restaurantId: Long = 0,

        var name: String = "",

        @Transient
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        var isDestroy: Boolean = false
)
