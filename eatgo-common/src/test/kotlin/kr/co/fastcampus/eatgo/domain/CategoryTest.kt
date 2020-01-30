package kr.co.fastcampus.eatgo.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class CategoryTest {
    @Test
    fun creation() {
        val category = Category(name = "Korean Food")

        Assertions.assertThat(category.name).isEqualTo("Korean Food")
    }
}
