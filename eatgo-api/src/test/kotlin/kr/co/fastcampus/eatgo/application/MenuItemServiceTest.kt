package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kr.co.fastcampus.eatgo.domain.MenuItem
import kr.co.fastcampus.eatgo.domain.MenuItemRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class MenuItemServiceTest {
    private lateinit var menuItemService: MenuItemService

    @Mock
    private lateinit var menuItemRepository: MenuItemRepository

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        menuItemService = MenuItemService(menuItemRepository)
    }

    @Test
    fun bulkUpdate() {
        val menuItems = mutableListOf<MenuItem>()

        menuItems.add(MenuItem(name = "Kimchi"))
        menuItems.add(MenuItem(id = 12, name = "Gukbob"))
        menuItems.add(MenuItem(id = 1004, isDestroy = true))

        menuItemService.bulkUpdate(1, menuItems)

        verify(menuItemRepository, times(2)).save(any())
        verify(menuItemRepository, times(1)).deleteById(eq(1004L))
    }
}
