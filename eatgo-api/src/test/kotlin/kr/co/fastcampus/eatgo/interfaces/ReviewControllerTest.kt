package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockitokotlin2.*
import kr.co.fastcampus.eatgo.application.ReviewService
import kr.co.fastcampus.eatgo.domain.Review
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ReviewController::class)
internal class ReviewControllerTest {
    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var reviewServive: ReviewService

    @Test
    fun createWithValidAttributes() {
        given(reviewServive.addReview(eq(1), any())).willReturn(
                Review(id = 1004, name = "Yunseok", score = 3,
                        description = "Mat-it-da")
        )

        mvc.perform(post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Yunsok\", \"score\": 3, \"description\": \"Mat-it-da\"}"))
                .andExpect(status().isCreated)
                .andExpect(header().string("location",
                        "/restaurants/1/reviews/1004"))

        verify(reviewServive).addReview(eq(1), any())
    }

    @Test
    fun createWithInvalidAttributes() {
        mvc.perform(post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest)

        verify(reviewServive, never()).addReview(eq(1), any())
    }
}
