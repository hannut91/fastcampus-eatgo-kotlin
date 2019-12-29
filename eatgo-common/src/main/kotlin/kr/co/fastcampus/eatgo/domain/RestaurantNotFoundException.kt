package kr.co.fastcampus.eatgo.domain

class RestaurantNotFoundException(id: Long) :
        RuntimeException("Could not find restaurant $id")
