package kr.co.fastcampus.eatgo.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RestaurantRepositoryImplTest {
    @Test
    fun save() {
        val repository: RestaurantRepository = RestaurantRepositoryImpl()

        val oldCount = repository.findAll().size

        val restaurant = Restaurant("BeRyong", "Seoul")
        repository.save(restaurant)

        assertThat(restaurant.getId()).isEqualTo(1234)

        val newCount = repository.findAll().size

        assertThat(newCount - oldCount).isEqualTo(1)
    }
}
