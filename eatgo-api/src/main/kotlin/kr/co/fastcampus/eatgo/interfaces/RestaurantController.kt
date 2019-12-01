package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.domain.Restaurant
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RestaurantController {
    @GetMapping("/restaurants")
    fun list(): List<Restaurant>? {
        val restaurants = mutableListOf<Restaurant>()

        val restaurant = Restaurant(1004, "Bob zip", "Seoul")

        restaurants.add(restaurant)

        return restaurants
    }
}
