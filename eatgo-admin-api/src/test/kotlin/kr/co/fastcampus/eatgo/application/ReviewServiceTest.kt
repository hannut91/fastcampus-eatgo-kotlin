package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockitokotlin2.given
import kr.co.fastcampus.eatgo.domain.Review
import kr.co.fastcampus.eatgo.domain.ReviewRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class ReviewServiceTest {
    private lateinit var reviewService: ReviewService

    @Mock
    private lateinit var reviewRepository: ReviewRepository

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        reviewService = ReviewService(reviewRepository)
    }

    @Test
    fun getReviews() {
        val mockReviews = arrayListOf<Review>()
        mockReviews.add(Review(description = "Cool!"))

        given(reviewRepository.findAll()).willReturn(mockReviews)

        val reviews = reviewService.getReviews()

        assertThat(reviews[0].description).isEqualTo("Cool!")
    }
}
