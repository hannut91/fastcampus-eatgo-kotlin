package kr.co.fastcampus.eatgo.domain

import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotEmpty

@Entity
class Restaurant(
        @Id
        @GeneratedValue
        var id: Long = 0,

        @field:NotEmpty
        var name: String = "",

        @field:NotEmpty
        var address: String = "",

        menuItems: ArrayList<MenuItem> = arrayListOf(),
        reviews: ArrayList<Review> = arrayListOf()
) {
    @Transient
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var menuItems = menuItems
        set(value) = run { field = ArrayList(value) }

    @Transient
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var reviews: ArrayList<Review> = reviews
        set(value) = run { field = ArrayList(value) }

    val information
        get() = "$name in $address"

    fun updateInformation(name: String, address: String) {
        this.name = name
        this.address = address
    }
}
