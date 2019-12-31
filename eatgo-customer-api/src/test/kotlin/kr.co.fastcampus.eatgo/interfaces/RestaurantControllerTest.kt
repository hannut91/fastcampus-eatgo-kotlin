package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RestaurantService
import kr.co.fastcampus.eatgo.domain.MenuItem
import kr.co.fastcampus.eatgo.domain.Restaurant
import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException
import kr.co.fastcampus.eatgo.domain.Review
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(RestaurantController::class)
internal class RestaurantControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var restaurantService: RestaurantService

    @Test
    fun list() {
        val restaurants = arrayListOf<Restaurant>()

        restaurants.add(
                Restaurant(id = 1004, name = "JOKER House",
                        address = "Seoul")
        )

        given(restaurantService.getRestaurants()).willReturn(restaurants)

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")
                ))
    }

    @Test
    fun detailWithExisted() {
        val restaurant = Restaurant(id = 1004, name = "JOKER House",
                address = "Seoul")
        val menuItem = MenuItem(name = "Kimchi")
        val review = Review(name = "Yunseok", score = 3, description = "Great!")

        restaurant.menuItems = arrayListOf(menuItem)
        restaurant.reviews = arrayListOf(review)

        given(restaurantService.getRestaurant(1004)).willReturn(restaurant)

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk)
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")
                ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ))
                .andExpect(content().string(
                        containsString("Great!")
                ))
    }

    @Test
    fun detailWithNotExisted() {
        given(restaurantService.getRestaurant(404))
                .willThrow(RestaurantNotFoundException(404))

        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound)
                .andExpect(content().string("{}"))
    }
}
