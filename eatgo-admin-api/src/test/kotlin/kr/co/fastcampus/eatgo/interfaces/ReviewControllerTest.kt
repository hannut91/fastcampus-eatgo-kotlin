package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockitokotlin2.given
import kr.co.fastcampus.eatgo.application.ReviewService
import kr.co.fastcampus.eatgo.domain.Review
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ReviewController::class)
internal class ReviewControllerTest {
    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var reviewService: ReviewService

    @Test
    fun list() {
        val reviews = arrayListOf<Review>()
        reviews.add(Review(description = "Cool!"))

        given(reviewService.getReviews()).willReturn(reviews);

        mvc.perform(get("/reviews"))
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("Cool!")))
    }
}
