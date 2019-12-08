package kr.co.fastcampus.eatgo.domain

import org.springframework.data.repository.CrudRepository

interface MenuItemRepository: CrudRepository<MenuItem, Long> {
    fun findAllByRestaurantId(restaurantId: Long): ArrayList<MenuItem>
}
