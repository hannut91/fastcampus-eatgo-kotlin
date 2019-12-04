package kr.co.fastcampus.eatgo.domain

interface MenuItemRepository {
    fun findAllByRestaurantId(restaurantId: Long): List<MenuItem>
}
