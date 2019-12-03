package kr.co.fastcampus.eatgo.domain

class Restaurant(
        val id: Long,
        var name: String,
        val address: String
) {
    fun getInformation() = "${name} in ${address}"
}
