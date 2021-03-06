package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockitokotlin2.any
import kr.co.fastcampus.eatgo.application.RestaurantService
import kr.co.fastcampus.eatgo.domain.Restaurant
import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.mockito.invocation.InvocationOnMock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

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
                Restaurant(id = 1004, categoryId = 1, name = "JOKER House",
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
        val restaurant = Restaurant(id = 1004, categoryId = 1,
                name = "JOKER House", address = "Seoul")

        given(restaurantService.getRestaurant(1004)).willReturn(restaurant)

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk)
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"JOKER House\"")
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

    @Test
    fun createWithValidData() {
        given(restaurantService.addRestaurant(any()))
                .will { invocation: InvocationOnMock ->
                    val restaurant = invocation.getArgument<Restaurant>(0)
                    Restaurant(id = 1234,
                            categoryId = 1,
                            name = restaurant.name,
                            address = restaurant.address)
                }


        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"categoryId\": 1, \"name\": \"BeRyong\", " +
                        "\"address\": \"Busan\"}"))
                .andExpect(status().isCreated)
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"))

        verify(restaurantService).addRestaurant(any())
    }

    @Test
    fun createWithInvalidData() {
        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"categoryId\": 1, \"name\": \"\", " +
                        "\"address\": \"\"}"))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun updateWithValidData() {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"categoryId\": 1, \"name\": \"JOKER Bar\", " +
                        "\"address\": \"Busan\"}"))
                .andExpect(status().isOk)

        verify(restaurantService).updateRestaurant(1004, "JOKER Bar", "Busan")
    }

    @Test
    fun updateWithInvalidData() {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"categoryId\": 1, \"name\": \"\", " +
                        "\"address\": \"\"}"))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun updateWithoutName() {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"categoryId\": 1, \"name\": \"\", " +
                        "\"address\": \"Busan\"}"))
                .andExpect(status().isBadRequest)
    }
}
