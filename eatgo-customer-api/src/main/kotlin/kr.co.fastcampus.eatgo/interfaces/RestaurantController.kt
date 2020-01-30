package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RestaurantService
import kr.co.fastcampus.eatgo.domain.Restaurant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
class RestaurantController {
    @Autowired
    private lateinit var restaurantService: RestaurantService

    @GetMapping("/restaurants")
    fun list(
            @RequestParam("region") region: String,
            @RequestParam("category") categoryId: Long
    ) = restaurantService.getRestaurants(region, categoryId)

    @GetMapping("/restaurants/{id}")
    fun detail(@PathVariable("id") id: Long): Restaurant? =
            restaurantService.getRestaurant(id)
}
