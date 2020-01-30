package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kr.co.fastcampus.eatgo.application.CategoryService
import kr.co.fastcampus.eatgo.domain.Category
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CategoryController::class)
internal class CategoryControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var categoryService: CategoryService

    @Test
    fun list() {
        val categories = arrayListOf<Category>()
        categories.add(Category(name = "Korean Food"))

        given(categoryService.getCategories()).willReturn(categories)

        mvc.perform(get("/categories"))
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("Korean Food")))
    }

    @Test
    fun create() {
        val category = Category(name = "Korean Food")

        given(categoryService.addCategory("Korean Food")).willReturn(category)

        mvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Korean Food\"}"))
                .andExpect(status().isCreated)
                .andExpect(content().string("{}"))

        verify(categoryService).addCategory("Korean Food")
    }
}
