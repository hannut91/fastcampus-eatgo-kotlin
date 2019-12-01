package com.example.demo.domain

class Restaurant {
    private val name: String
    private val address: String

    constructor(name: String, address: String) {
        this.name = name
        this.address = address
    }

    fun getName() = name

    fun getAddress() = address

    fun getInformation() = "${name} in ${address}"
}
