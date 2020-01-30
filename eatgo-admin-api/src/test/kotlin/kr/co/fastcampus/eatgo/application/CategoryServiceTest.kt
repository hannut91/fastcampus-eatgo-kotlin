package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kr.co.fastcampus.eatgo.domain.Category
import kr.co.fastcampus.eatgo.domain.CategoryRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class CategoryServiceTest {
    private lateinit var categoryService: CategoryService

    @Mock
    private lateinit var categoryRepository: CategoryRepository

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)

        categoryService = CategoryService(categoryRepository)
    }

    @Test
    fun getCategories() {
        val mockCategories = arrayListOf<Category>()
        mockCategories.add(Category(name = "Seoul"))

        given(categoryRepository.findAll()).willReturn(mockCategories)

        val categories = categoryService.getCategories()

        Assertions.assertThat(categories.first().name).isEqualTo("Seoul")
    }

    @Test
    fun addRegion() {
        val category = categoryService.addCategory("Seoul")

        verify(categoryRepository).save(any<Category>())

        Assertions.assertThat(category.name).isEqualTo("Seoul")
    }
}
