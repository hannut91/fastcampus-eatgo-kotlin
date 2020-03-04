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
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.8hm6ZOJykSINHxL-rf0yV882fApL3hyQ9-WGlJUyo2A"

        given(reviewServive.addReview(1L, "John", 3, "Mat-it-da"))
                .willReturn(Review(id = 1004, name = "John", score = 3,
                        description = "Mat-it-da"))

        mvc.perform(post("/restaurants/1/reviews")
                .header("Authorization", "Bearer $token")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"score\": 3, \"description\": \"Mat-it-da\"}"))
                .andExpect(status().isCreated)
                .andExpect(header().string("location",
                        "/restaurants/1/reviews/1004"))

        verify(reviewServive)
                .addReview(eq(1), eq("John"), eq(3), eq("Mat-it-da"))
    }

    @Test
    fun createWithInvalidAttributes() {
        mvc.perform(post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest)

        verify(reviewServive, never()).addReview(any(), any(), any(), any())
    }
}
