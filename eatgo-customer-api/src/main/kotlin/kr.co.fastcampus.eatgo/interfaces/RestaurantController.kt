package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RestaurantService
import kr.co.fastcampus.eatgo.domain.Restaurant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
class RestaurantController {
    @Autowired
    private lateinit var restaurantService: RestaurantService

    @GetMapping("/restaurants")
    fun list(): ArrayList<Restaurant> = restaurantService.getRestaurants()

    @GetMapping("/restaurants/{id}")
    fun detail(@PathVariable("id") id: Long): Restaurant? =
            restaurantService.getRestaurant(id)
}
