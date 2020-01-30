package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class RestaurantService {
    private val restaurantRepository: RestaurantRepository
    private val menuItemRepository: MenuItemRepository
    private val reviewRepository: ReviewRepository

    @Autowired
    constructor(restaurantRepository: RestaurantRepository,
                menuItemRepository: MenuItemRepository,
                reviewRepository: ReviewRepository) {
        this.restaurantRepository = restaurantRepository
        this.menuItemRepository = menuItemRepository
        this.reviewRepository = reviewRepository
    }

    fun getRestaurant(id: Long): Restaurant? {
        val restaurant = restaurantRepository.findById(id)
                .orElseThrow { RestaurantNotFoundException(id) }

        val menuItems = menuItemRepository.findAllByRestaurantId(id)
        restaurant.menuItems = menuItems

        val reviews = reviewRepository.findAllByRestaurantId(id)
        restaurant.reviews = reviews

        return restaurant
    }

    fun getRestaurants(region: String, categoryId: Long) =
            restaurantRepository
                    .findAllByAddressContainingAndCategoryId(region, categoryId)

    fun addRestaurant(restaurant: Restaurant) =
            restaurantRepository.save(restaurant)

    @Transactional
    fun updateRestaurant(id: Long, name: String, address: String): Restaurant? {
        val restaurant = restaurantRepository.findById(id).orElse(null)

        restaurant.updateInformation(name, address)

        return restaurant
    }
}
