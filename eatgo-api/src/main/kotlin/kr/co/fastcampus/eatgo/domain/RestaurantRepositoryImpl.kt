package kr.co.fastcampus.eatgo.domain

import org.springframework.stereotype.Component

@Component
class RestaurantRepositoryImpl : RestaurantRepository {
    private val restaurants = arrayListOf<Restaurant>()

    constructor() {
        restaurants.add(Restaurant(1004, "Bob zip", "Seoul"))
        restaurants.add(Restaurant(2020, "Cyber food", "Seoul"))
    }

    override fun findAll(): List<Restaurant> {
        return restaurants
    }

    override fun findById(id: Long): Restaurant? {
        return restaurants.firstOrNull { it.id == id }
    }
}
