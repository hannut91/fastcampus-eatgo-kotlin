package kr.co.fastcampus.eatgo.domain

import org.springframework.stereotype.Component

@Component
class RestaurantRepositoryImpl : RestaurantRepository {
    private val restaurants = arrayListOf<Restaurant>()

    constructor() {
        restaurants.add(Restaurant(1004, "Bob zip", "Seoul"))
        restaurants.add(Restaurant(2020, "Cyber food", "Seoul"))
    }

    override fun findAll(): ArrayList<Restaurant> = restaurants

    override fun findById(id: Long) =
            restaurants.firstOrNull { it.getId() == id }

    override fun save(restaurant: Restaurant): Restaurant {
        restaurant.setId(1234)
        restaurants.add(restaurant)
        return restaurant
    }
}
