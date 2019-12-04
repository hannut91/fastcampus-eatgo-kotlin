package kr.co.fastcampus.eatgo.domain

class Restaurant(
        val id: Long,
        var name: String,
        val address: String,
        val menuItems: ArrayList<MenuItem> = arrayListOf()
) {
    fun getInformation() = "$name in $address"

    fun setMenuItems(menuItems: List<MenuItem>) {
        menuItems.forEach { addMenuItem(it) }
    }

    private fun addMenuItem(menuItem: MenuItem) {
        menuItems.add(menuItem)
    }
}
