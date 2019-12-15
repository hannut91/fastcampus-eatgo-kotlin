package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RestaurantService
import kr.co.fastcampus.eatgo.domain.Restaurant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

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
    fun create(@Valid @RequestBody resource: Restaurant):
            ResponseEntity<String> {
        val name = resource.name
        val address = resource.address

        val restaurant = restaurantService.addRestaurant(
                Restaurant.Builder()
                        .name(name)
                        .address(address)
                        .build()
        )

        val location = URI("/restaurants/${restaurant.id}")
        return ResponseEntity.created(location).body("{}")
    }

    @PatchMapping("/restaurants/{id}")
    fun update(
            @PathVariable("id") id: Long,
            @Valid @RequestBody resource: Restaurant
    ): String {
        val name = resource.name
        val address = resource.address
        restaurantService.updateRestaurant(id, name, address)

        return "{}"
    }
}
