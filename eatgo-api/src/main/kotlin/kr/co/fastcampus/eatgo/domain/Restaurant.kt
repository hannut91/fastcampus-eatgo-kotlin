package kr.co.fastcampus.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Transient

@Entity
class Restaurant {
    @Id
    @GeneratedValue
    private var id: Long = 0
    private var name: String = ""
    private var address: String = ""

    @Transient
    private var menuItems: ArrayList<MenuItem> = arrayListOf()

    constructor() {}

    constructor(name: String, address: String) {
        this.name = name
        this.address = address
    }

    constructor(
            id: Long,
            name: String,
            address: String,
            menuItems: ArrayList<MenuItem> = arrayListOf()
    ) {
        this.id = id
        this.name = name
        this.address = address
        this.menuItems = menuItems
    }

    fun getId() = id

    fun setId(id: Long) {
        this.id = id
    }

    fun getName() = name

    fun getAddress() = address

    fun getMenuItems() = menuItems

    fun setMenuItems(menuItems: ArrayList<MenuItem>) {
        menuItems.forEach { addMenuItem(it) }
    }

    fun getInformation() = "$name in $address"

    fun addMenuItem(menuItem: MenuItem) {
        menuItems.add(menuItem)
    }
}
