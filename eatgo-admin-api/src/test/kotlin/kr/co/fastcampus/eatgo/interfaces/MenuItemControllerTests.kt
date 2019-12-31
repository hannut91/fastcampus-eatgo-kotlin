package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kr.co.fastcampus.eatgo.application.MenuItemService
import kr.co.fastcampus.eatgo.domain.MenuItem
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(MenuItemController::class)
internal class MenuItemControllerTests {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var menuItemService: MenuItemService

    @Test
    fun list() {
        val menuItems = arrayListOf<MenuItem>()
        menuItems.add(MenuItem(name = "Kimchi"))

        given(menuItemService.getMenuItems(1L)).willReturn(menuItems)

        mvc.perform(get("/restaurants/1/menuitems"))
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("Kimchi")))
    }

    @Test
    fun bulkUpdate() {
        mvc.perform(patch("/restaurants/1/menuitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]"))
                .andExpect(status().isOk)

        verify(menuItemService).bulkUpdate(eq(1), any())
    }
}
