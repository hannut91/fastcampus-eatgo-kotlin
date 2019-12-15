package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import kr.co.fastcampus.eatgo.application.MenuItemService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(MenuItemController::class)
internal class MenuItemControllerTests {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var menuItemService: MenuItemService

    @Test
    fun bulkUpdate() {
        mvc.perform(patch("/restaurants/1/menuitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]"))
                .andExpect(status().isOk)

        verify(menuItemService).bulkUpdate(eq(1), any())
    }
}
