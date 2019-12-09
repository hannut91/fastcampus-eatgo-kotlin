package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.MenuItemRepository
import kr.co.fastcampus.eatgo.domain.Restaurant
import kr.co.fastcampus.eatgo.domain.RestaurantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

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
        val restaurant = restaurantRepository.findById(id).orElse(null)

        val menuItems = menuItemRepository.findAllByRestaurantId(id)

        restaurant?.setMenuItems(menuItems)

        return restaurant
    }

    fun getRestaurants(): ArrayList<Restaurant> = restaurantRepository.findAll()

    fun addRestaurant(restaurant: Restaurant) =
            restaurantRepository.save(restaurant)

    @Transactional
    fun updateRestaurant(id: Long, name: String, address: String): Restaurant? {
        val restaurant = restaurantRepository.findById(id).orElse(null)

        restaurant.updateInformation(name, address)

        return restaurant
    }
}
