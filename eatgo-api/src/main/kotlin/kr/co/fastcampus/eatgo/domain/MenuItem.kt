package kr.co.fastcampus.eatgo.domain

import com.fasterxml.jackson.annotation.JsonInclude
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

    @Transient
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    var isDestroy = false

    constructor()

    constructor(
            id: Long,
            restaurantId: Long,
            name: String,
            isDestroy: Boolean
    ) {
        this.id = id
        this.restaurantId = restaurantId
        this.name = name
        this.isDestroy = isDestroy
    }

    data class Builder(
            var id: Long = 0,
            var name: String = "",
            var restaurantId: Long = 0,
            var isDestroy: Boolean = false
    ) {
        fun id(id: Long) = apply { this.id = id }
        fun restaurantId(id: Long) = apply { this.restaurantId = id }
        fun name(name: String) = apply { this.name = name }
        fun isDestroy(isDestroy: Boolean) = apply { this.isDestroy = isDestroy }

        fun build() = MenuItem(id, restaurantId, name, isDestroy)
    }
}
