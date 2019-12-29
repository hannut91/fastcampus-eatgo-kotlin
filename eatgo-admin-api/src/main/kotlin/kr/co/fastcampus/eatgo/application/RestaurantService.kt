package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.Restaurant
import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException
import kr.co.fastcampus.eatgo.domain.RestaurantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class RestaurantService {
    private val restaurantRepository: RestaurantRepository

    @Autowired
    constructor(restaurantRepository: RestaurantRepository) {
        this.restaurantRepository = restaurantRepository
    }

    fun getRestaurant(id: Long): Restaurant? =
            restaurantRepository
                    .findById(id)
                    .orElseThrow { RestaurantNotFoundException(id) }

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
