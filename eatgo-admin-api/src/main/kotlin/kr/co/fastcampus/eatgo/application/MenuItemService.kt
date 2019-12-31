package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.MenuItem
import kr.co.fastcampus.eatgo.domain.MenuItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MenuItemService(
        @Autowired private val menuItemRepository: MenuItemRepository
) {
    fun getMenuItems(restaurantId: Long) =
            menuItemRepository.findAllByRestaurantId(restaurantId)

    fun bulkUpdate(restaurantId: Long, menuItems: List<MenuItem>) {
        menuItems.forEach { update(restaurantId, it) }
    }

    private fun update(restaurantId: Long, menuItem: MenuItem) {
        if (menuItem.isDestroy) {
            menuItemRepository.deleteById(menuItem.id)
            return
        }

        menuItem.restaurantId = restaurantId
        menuItemRepository.save(menuItem)
    }
}
