package kr.co.fastcampus.eatgo.domain

import org.springframework.stereotype.Component

@Component
class MenuItemRepositoryImpl: MenuItemRepository {
    private val menuItems = arrayListOf<MenuItem>()

    constructor() {
        menuItems.add(MenuItem("Kimchi"))
    }

    override fun findAllByRestaurantId(restaurantId: Long): ArrayList<MenuItem> {
        return menuItems
    }
}
