package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockitokotlin2.given
import kr.co.fastcampus.eatgo.domain.Region
import kr.co.fastcampus.eatgo.domain.RegionRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class RegionServiceTest {
    private lateinit var regionService: RegionService

    @Mock
    private lateinit var regionRepository: RegionRepository

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)

        regionService = RegionService(regionRepository)
    }

    @Test
    fun getRegions() {
        val mockRegions = arrayListOf<Region>()
        mockRegions.add(Region(name = "Seoul"))

        given(regionRepository.findAll()).willReturn(mockRegions)

        val regions = regionService.getRegions()

        assertThat(regions.first().name).isEqualTo("Seoul")
    }
}
