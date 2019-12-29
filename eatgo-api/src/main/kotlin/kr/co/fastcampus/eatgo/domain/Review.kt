package kr.co.fastcampus.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

@Entity
class Review(
        @Id
        @GeneratedValue
        var id: Long = 0,

        var restaurantId: Long = 0,

        @field:NotEmpty
        var name: String = "",

        @field:Min(0)
        @field:Max(5)
        var score: Int = 0,

        @field:NotEmpty
        var description: String = ""
)
