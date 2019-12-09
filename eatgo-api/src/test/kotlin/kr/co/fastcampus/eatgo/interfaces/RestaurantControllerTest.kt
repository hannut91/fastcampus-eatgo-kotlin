package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockitokotlin2.any
import kr.co.fastcampus.eatgo.application.RestaurantService
import kr.co.fastcampus.eatgo.domain.MenuItem
import kr.co.fastcampus.eatgo.domain.Restaurant
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import org.mockito.invocation.InvocationOnMock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


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

        restaurants.add(Restaurant.Builder()
                .id(1004)
                .name("JOKER House")
                .address("Seoul")
                .build())

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
        val restaurant1 = Restaurant.Builder()
                .id(1004)
                .name("JOKER House")
                .address("Seoul")
                .build()
        restaurant1.menuItems = arrayListOf(
                MenuItem.Builder()
                        .name("Kimchi")
                        .build()
        )

        val restaurant2 = Restaurant.Builder()
                .id(2020)
                .name("Cyber food")
                .address("Seoul")
                .build()

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

    @Test
    fun create() {
        given(restaurantService.addRestaurant(any()))
                .will { invocation: InvocationOnMock ->
                    val restaurant = invocation.getArgument<Restaurant>(0)
                    Restaurant.Builder()
                            .id(1234)
                            .name(restaurant.name)
                            .address(restaurant.address)
                            .build()
                }

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"BeRyong\", \"address\": \"Busan\"}"))
                .andExpect(status().isCreated)
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"))

        verify(restaurantService).addRestaurant(any())
    }

    @Test
    fun update() {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"JOKER Bar\", \"address\": \"Busan\"}"))
                .andExpect(status().isOk)

        verify(restaurantService).updateRestaurant(1004, "JOKER Bar", "Busan")
    }
}
