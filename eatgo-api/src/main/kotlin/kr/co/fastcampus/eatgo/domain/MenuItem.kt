package kr.co.fastcampus.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class MenuItem {
    @Id
    @GeneratedValue
    var id: Long = 0

    var restaurantId: Long = 0

    var name: String = ""

    constructor() {}

    constructor(id: Long, restaurantId: Long, name: String) {
        this.id = id
        this.restaurantId = restaurantId
        this.name = name
    }

    data class Builder(
            var id: Long = 0,
            var name: String = "",
            var restaurantId: Long = 0
    ) {
        fun id(id: Long) = apply { this.id = id }
        fun restaurantId(id: Long) = apply { this.restaurantId = id }
        fun name(name: String) = apply { this.name = name }

        fun build() = MenuItem(id, restaurantId, name)
    }
}
