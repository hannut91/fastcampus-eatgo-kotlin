package kr.co.fastcampus.eatgo.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RestaurantTests {
    @Test
    fun creation() {
        val restaurant = Restaurant(1004, "Bob zip", "Seoul")

        assertThat(restaurant.id).isEqualTo(1004)
        assertThat(restaurant.name).isEqualTo("Bob zip")
        assertThat(restaurant.address).isEqualTo("Seoul")
    }

    @Test
    fun information() {
        val restaurant = Restaurant(1004, "Bob zip", "Seoul")

        assertThat(restaurant.getInformation()).isEqualTo("Bob zip in Seoul")
    }
}
