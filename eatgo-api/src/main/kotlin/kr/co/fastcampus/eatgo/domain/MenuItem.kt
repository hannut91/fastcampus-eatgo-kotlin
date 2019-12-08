package kr.co.fastcampus.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class MenuItem(val name: String) {
    @Id
    @GeneratedValue
    private var id: Long = 0

    private var restaurantId: Long = 0
}
