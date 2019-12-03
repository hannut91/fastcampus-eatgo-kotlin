package kr.co.fastcampus.eatgo.domain

class RestaurantRepository {
    private val restaurants = arrayListOf<Restaurant>()

    constructor() {
        restaurants.add(Restaurant(1004, "Bob zip", "Seoul"))
        restaurants.add(Restaurant(2020, "Cyber food", "Seoul"))
    }

    fun findAll(): List<Restaurant> {
        return restaurants
    }

    fun findById(id: Long): Restaurant? {
        return restaurants.firstOrNull { it.id.equals(id) }
    }
}
