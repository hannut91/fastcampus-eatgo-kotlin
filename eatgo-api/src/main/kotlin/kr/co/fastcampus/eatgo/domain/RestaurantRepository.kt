package kr.co.fastcampus.eatgo.domain

import org.springframework.data.repository.CrudRepository
import java.util.*
import kotlin.collections.ArrayList

interface RestaurantRepository: CrudRepository<Restaurant, Long> {
    override fun findAll(): ArrayList<Restaurant>
    override fun findById(id: Long): Optional<Restaurant>
    fun save(restaurant: Restaurant): Restaurant
}
