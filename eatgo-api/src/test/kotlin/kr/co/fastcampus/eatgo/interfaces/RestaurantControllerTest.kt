package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RestaurantService
import kr.co.fastcampus.eatgo.domain.MenuItem
import kr.co.fastcampus.eatgo.domain.Restaurant
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(RestaurantController::class)
internal class RestaurantControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var restaurantService: RestaurantService

    @Test
    fun list() {
        val restaurants = arrayListOf<Restaurant>()
        restaurants.add(Restaurant(1004, "JOKER House", "Seoul"))

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
    fun detail() {
        val restaurant1 = Restaurant(1004, "JOKER House", "Seoul")
        restaurant1.addMenuItem(MenuItem("Kimchi"))

        val restaurant2 = Restaurant(2020, "Cyber food", "Seoul")

        given(restaurantService.getRestaurant(1004)).willReturn(restaurant1)
        given(restaurantService.getRestaurant(2020)).willReturn(restaurant2)

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

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk)
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber food\"")
                ))
    }
}
