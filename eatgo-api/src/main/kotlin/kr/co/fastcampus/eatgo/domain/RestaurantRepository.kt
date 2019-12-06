package kr.co.fastcampus.eatgo.domain

interface RestaurantRepository {
    fun findAll(): ArrayList<Restaurant>
    fun findById(id: Long): Restaurant?
}
