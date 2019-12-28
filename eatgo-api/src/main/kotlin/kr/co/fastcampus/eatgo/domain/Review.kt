package kr.co.fastcampus.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

@Entity
class Review {
    @Id
    @GeneratedValue
    var id: Long = 0

    var restaurantId: Long = 0

    @NotEmpty
    var name: String = ""

    @Min(0)
    @Max(5)
    var score: Int = 0

    @NotEmpty
    var description: String = ""

    constructor()

    constructor(
            id: Long = 0,
            name: String = "",
            score: Int = 0,
            description: String = ""
    ) {
        this.id = id
        this.name = name
        this.score = score
        this.description = description
    }

    data class Builder(
            var id: Long = 0,
            var name: String = "",
            var score: Int = 0,
            var description: String = ""
    ) {
        fun id(id: Long) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun score(score: Int) = apply { this.score = score }
        fun description(description: String) =
                apply { this.description = description }

        fun build() = Review(id, name, score, description)
    }
}
