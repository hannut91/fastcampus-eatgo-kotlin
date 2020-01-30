package kr.co.fastcampus.eatgo.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RegionTest {
    @Test
    fun creation() {
        val region = Region(name = "서울")

        assertThat(region.name).isEqualTo("서울")
    }
}
