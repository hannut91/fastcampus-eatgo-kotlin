package kr.co.fastcampus.eatgo.domain

class Restaurant(
        private val id: Long,
        private var name: String,
        private val address: String
) {
    fun getId() = id

    fun getName() = name

    fun getAddress() = address

    fun getInformation() = "${name} in ${address}"
}
