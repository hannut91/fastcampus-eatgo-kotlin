package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockitokotlin2.given
import kr.co.fastcampus.eatgo.application.RegionService
import kr.co.fastcampus.eatgo.domain.Region
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(RegionController::class)
internal class RegionControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var regionService: RegionService

    @Test
    fun list() {
        val regions = arrayListOf<Region>()
        regions.add(Region(name = "Seoul"))

        given(regionService.getRegions()).willReturn(regions)

        mvc.perform(get("/regions"))
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("Seoul")))
    }
}
