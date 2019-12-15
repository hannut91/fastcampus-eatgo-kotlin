package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.MenuItemService
import kr.co.fastcampus.eatgo.domain.MenuItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MenuItemController {
    @Autowired
    private lateinit var menuItemService: MenuItemService

    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    fun bulkUpdate(
            @PathVariable("restaurantId") restaurantId: Long,
            @RequestBody menuItems: List<MenuItem>
    ): String {
        menuItemService.bulkUpdate(restaurantId, menuItems)
        return ""
    }
}
