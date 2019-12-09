package kr.co.fastcampus.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Restaurant {
    @Id
    @GeneratedValue
    var id: Long = 0
    var name: String = ""
    var address: String = ""
    @Transient
    var menuItems: ArrayList<MenuItem> = arrayListOf()
        set(value) = run { field = ArrayList(value) }

    val information
        get() = "$name in $address"

    constructor () {}

    constructor(
            id: Long = 0,
            name: String = "",
            address: String = "",
            menuItems: ArrayList<MenuItem> = arrayListOf()
    ) {
        this.id = id
        this.name = name
        this.address = address
        this.menuItems = menuItems
    }

    data class Builder(
            var id: Long = 0,
            var name: String = "",
            var address: String = "",
            var menuItems: ArrayList<MenuItem> = arrayListOf()
    ) {
        fun id(id: Long) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun address(address: String) = apply { this.address = address }
        fun menuItems(menuItems: ArrayList<MenuItem>) =
                apply { this.menuItems = menuItems }

        fun build() = Restaurant(id, name, address, menuItems)
    }

    fun updateInformation(name: String, address: String) {
        this.name = name
        this.address = address
    }
}
