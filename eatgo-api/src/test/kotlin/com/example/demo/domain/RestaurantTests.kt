package com.example.demo.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RestaurantTests {
    @Test
    fun creation() {
        val restaurant = Restaurant("Bob zip", "Seoul")

        assertThat(restaurant.getName()).isEqualTo("Bob zip")
        assertThat(restaurant.getAddress()).isEqualTo("Seoul")
    }

    @Test
    fun information() {
        val restaurant = Restaurant("Bob zip", "Seoul")

        assertThat(restaurant.getInformation()).isEqualTo("Bob zip in Seoul")
    }
}
