package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RestaurantService
import kr.co.fastcampus.eatgo.domain.Restaurant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

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

    @PostMapping("/restaurants")
    fun create(@RequestBody resource: Restaurant): ResponseEntity<String> {
        val name = resource.getName()
        val address = resource.getAddress()

        val restaurant = Restaurant(name, address)
        restaurantService.addRestaurant(restaurant)

        val location = URI("/restaurants/${restaurant.getId()}")
        return ResponseEntity.created(location).body("{}")
    }
}
