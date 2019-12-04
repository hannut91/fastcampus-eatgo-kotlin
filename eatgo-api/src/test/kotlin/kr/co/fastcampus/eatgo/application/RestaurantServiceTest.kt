package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.MenuItemRepository
import kr.co.fastcampus.eatgo.domain.MenuItemRepositoryImpl
import kr.co.fastcampus.eatgo.domain.RestaurantRepository
import kr.co.fastcampus.eatgo.domain.RestaurantRepositoryImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class RestaurantServiceTest {
    private lateinit var restaurantService: RestaurantService

    private lateinit var restaurantRepository: RestaurantRepository

    private lateinit var menuItemRepository: MenuItemRepository

    @BeforeEach
    fun setUp() {
        restaurantRepository = RestaurantRepositoryImpl()

        menuItemRepository = MenuItemRepositoryImpl()

        restaurantService = RestaurantService(restaurantRepository,
                menuItemRepository)
    }

    @Test
    fun getRestaurants() {
        val restaurants = restaurantService.getRestaurants()

        assertThat(restaurants[0].id).isEqualTo(1004)
    }

    @Test
    fun getRestaurant() {
        val restaurant = restaurantService.getRestaurant(1004)

        assertThat(restaurant?.id).isEqualTo(1004)
        assertThat(restaurant?.menuItems?.get(0)?.name).isEqualTo("Kimchi")
    }
}
