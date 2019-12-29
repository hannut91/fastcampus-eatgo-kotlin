package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockitokotlin2.any
import kr.co.fastcampus.eatgo.domain.Restaurant
import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException
import kr.co.fastcampus.eatgo.domain.RestaurantRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*

internal class RestaurantServiceTest {
    private lateinit var restaurantService: RestaurantService

    @Mock
    private lateinit var restaurantRepository: RestaurantRepository

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mockRestaurantRepository()

        restaurantService = RestaurantService(restaurantRepository)
    }

    private fun mockRestaurantRepository() {
        val restaurants = arrayListOf<Restaurant>()
        val restaurant = Restaurant(id = 1004, name = "Bob zip",
                address = "Seoul")

        restaurants.add(restaurant)

        given(restaurantRepository.findAll()).willReturn(restaurants)

        given(restaurantRepository.findById(1004))
                .willReturn(Optional.of(restaurant))
    }

    @Test
    fun getRestaurants() {
        val restaurants = restaurantService.getRestaurants()

        assertThat(restaurants[0].id).isEqualTo(1004)
    }

    @Test
    fun getRestaurantWithExisted() {
        val restaurant = restaurantService.getRestaurant(1004)

        assertThat(restaurant?.id).isEqualTo(1004)
    }

    @Test()
    fun getRestaurantWithNotExisted() {
        assertThrows<RestaurantNotFoundException> {
            restaurantService.getRestaurant(404)
        }
    }

    @Test()
    fun addRestaurant() {
        val restaurant = Restaurant(name = "BeRyong", address = "Busan")

        val saved = Restaurant(id = 1234, name = "BeRyong", address = "Busan")

        given(restaurantRepository.save(any())).willReturn(saved)

        val created = restaurantService.addRestaurant(restaurant)

        assertThat(created.id).isEqualTo(1234)
    }

    @Test
    fun updateRestaurant() {
        val restaurant = Restaurant(id = 1004, name = "Bob zip",
                address = "Seoul")

        given(restaurantRepository.findById(1004))
                .willReturn(Optional.of(restaurant))

        restaurantService.updateRestaurant(1004, "Sool zip", "Busan")

        assertThat(restaurant.name).isEqualTo("Sool zip")
        assertThat(restaurant.address).isEqualTo("Busan")
    }
}
