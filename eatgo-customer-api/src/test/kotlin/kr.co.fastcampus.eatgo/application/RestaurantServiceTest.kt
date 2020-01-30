package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
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

    @Mock
    private lateinit var reviewRepository: ReviewRepository

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mockRestaurantRepository()
        mockMenuItemRepository()
        mockReviewRespository()

        restaurantService = RestaurantService(
                restaurantRepository, menuItemRepository, reviewRepository)
    }

    private fun mockRestaurantRepository() {
        val restaurants = arrayListOf<Restaurant>()
        val restaurant = Restaurant(id = 1004, name = "Bob zip",
                address = "Seoul")

        restaurants.add(restaurant)

        given(restaurantRepository
                .findAllByAddressContainingAndCategoryId("Seoul", 1))
                .willReturn(restaurants)

        given(restaurantRepository.findById(1004))
                .willReturn(Optional.of(restaurant))
    }

    private fun mockMenuItemRepository() {
        val menuItems = arrayListOf<MenuItem>()
        menuItems.add(MenuItem(name = "Kimchi"))

        given(menuItemRepository.findAllByRestaurantId(1004))
                .willReturn(menuItems)
    }

    private fun mockReviewRespository() {
        val reviews = arrayListOf<Review>()

        reviews.add(Review(name = "BeRyong", score = 1, description = "Bad"))

        given(reviewRepository.findAllByRestaurantId(1004))
                .willReturn(reviews)
    }

    @Test
    fun getRestaurants() {
        val categoryId = 1L
        val restaurants = restaurantService.getRestaurants("Seoul", categoryId)

        assertThat(restaurants[0].id).isEqualTo(1004)
    }

    @Test
    fun getRestaurantWithExisted() {
        val restaurant = restaurantService.getRestaurant(1004)

        verify(menuItemRepository).findAllByRestaurantId(eq(1004))
        verify(reviewRepository).findAllByRestaurantId(eq(1004))

        assertThat(restaurant?.id).isEqualTo(1004)
        assertThat(restaurant?.menuItems?.get(0)?.name).isEqualTo("Kimchi")
        assertThat(restaurant?.reviews?.get(0)?.description).isEqualTo("Bad")
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
