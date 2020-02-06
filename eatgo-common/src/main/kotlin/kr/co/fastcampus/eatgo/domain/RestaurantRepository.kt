package kr.co.fastcampus.eatgo.domain

import org.springframework.data.repository.CrudRepository
import java.util.*

interface RestaurantRepository : CrudRepository<Restaurant, Long> {
    
    override fun findAll(): ArrayList<Restaurant>

    fun findAllByAddressContainingAndCategoryId(
            region: String,
            categoryId: Long
    ): List<Restaurant>

    override fun findById(id: Long): Optional<Restaurant>

    fun save(restaurant: Restaurant): Restaurant
}
