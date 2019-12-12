package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockitokotlin2.any
import kr.co.fastcampus.eatgo.domain.*
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

    @Mock
    private lateinit var menuItemRepository: MenuItemRepository

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mockRestaurantRepository()
        mockMenuItemRepository()

        restaurantService = RestaurantService(
                restaurantRepository, menuItemRepository)
    }

    private fun mockRestaurantRepository() {
        val restaurants = arrayListOf<Restaurant>()
        val restaurant = Restaurant.Builder()
                .id(1004)
                .name("Bob zip")
                .address("Seoul")
                .build()

        restaurants.add(restaurant)

        given(restaurantRepository.findAll()).willReturn(restaurants)

        given(restaurantRepository.findById(1004))
                .willReturn(Optional.of(restaurant))
    }

    private fun mockMenuItemRepository() {
        val menuItems = arrayListOf<MenuItem>()
        menuItems.add(MenuItem.Builder().name("Kimchi").build())

        given(menuItemRepository.findAllByRestaurantId(1004))
                .willReturn(menuItems)
    }

    @Test
    fun getRestaurants() {
        val restaurants = restaurantService.getRestaurants()

        assertThat(restaurants[0].id).isEqualTo(1004)
    }

    @Test
    fun getRestaurantWithExisted() {
        val restaurant = restaurantService.getRestaurant(1004)

        print(restaurant)

        assertThat(restaurant?.id).isEqualTo(1004)
        assertThat(restaurant?.menuItems?.get(0)?.name).isEqualTo("Kimchi")
    }

    @Test()
    fun getRestaurantWithNotExisted() {
        assertThrows<RestaurantNotFoundException> {
            restaurantService.getRestaurant(404)
        }
    }

    @Test()
    fun addRestaurant() {
        val restaurant = Restaurant.Builder()
                .name("BeRyong")
                .address("Busan")
                .build()

        val saved = Restaurant.Builder()
                .id(1234)
                .name("BeRyong")
                .address("Busan")
                .build()

        given(restaurantRepository.save(any())).willReturn(saved)

        val created = restaurantService.addRestaurant(restaurant)

        assertThat(created.id).isEqualTo(1234)
    }

    @Test
    fun updateRestaurant() {
        val restaurant = Restaurant.Builder()
                .id(1004)
                .name("Bob zip")
                .address("Seoul")
                .build()

        given(restaurantRepository.findById(1004))
                .willReturn(Optional.of(restaurant))

        restaurantService.updateRestaurant(1004, "Sool zip", "Busan")

        assertThat(restaurant.name).isEqualTo("Sool zip")
        assertThat(restaurant.address).isEqualTo("Busan")
    }
}
