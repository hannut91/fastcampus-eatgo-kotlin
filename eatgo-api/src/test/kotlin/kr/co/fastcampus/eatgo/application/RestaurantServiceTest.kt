package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockitokotlin2.any
import kr.co.fastcampus.eatgo.domain.MenuItem
import kr.co.fastcampus.eatgo.domain.MenuItemRepository
import kr.co.fastcampus.eatgo.domain.Restaurant
import kr.co.fastcampus.eatgo.domain.RestaurantRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

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
        val restaurant = Restaurant(1004, "Bob zip", "Seoul")
        restaurants.add(restaurant)

        given(restaurantRepository.findAll()).willReturn(restaurants)

        given(restaurantRepository.findById(1004)).willReturn(restaurant)
    }

    private fun mockMenuItemRepository() {
        val menuItems = arrayListOf<MenuItem>()
        menuItems.add(MenuItem("Kimchi"))

        given(menuItemRepository.findAllByRestaurantId(1004))
                .willReturn(menuItems)
    }

    @Test
    fun getRestaurants() {
        val restaurants = restaurantService.getRestaurants()

        assertThat(restaurants[0].getId()).isEqualTo(1004)
    }

    @Test
    fun getRestaurant() {
        val restaurant = restaurantService.getRestaurant(1004)

        assertThat(restaurant?.getId()).isEqualTo(1004)
        assertThat(restaurant?.getMenuItems()?.get(0)?.name).isEqualTo("Kimchi")
    }

    @Test
    fun addRestaurant() {
        val restaurant = Restaurant("BeRyong", "Busan")
        val saved = Restaurant(1234, "BeRyong", "Busan")

        given(restaurantRepository.save(any())).willReturn(saved)

        val created = restaurantService.addRestaurant(restaurant)

        assertThat(created.getId()).isEqualTo(1234)
    }
}
