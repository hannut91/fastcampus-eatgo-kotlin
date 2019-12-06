package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RestaurantService {
    @Autowired
    private val restaurantRepository: RestaurantRepository

    @Autowired
    private val menuItemRepository: MenuItemRepository

    constructor(restaurantRepository: RestaurantRepository,
                menuItemRepository: MenuItemRepository) {
        this.restaurantRepository = restaurantRepository
        this.menuItemRepository = menuItemRepository
    }

    fun getRestaurant(id: Long): Restaurant? {
        val restaurant = restaurantRepository.findById(id)

        val menuItems = menuItemRepository.findAllByRestaurantId(id)

        restaurant?.setMenuItems(menuItems)

        return restaurant
    }

    fun getRestaurants(): ArrayList<Restaurant> {
        return restaurantRepository.findAll()
    }
}
