package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import kr.co.fastcampus.eatgo.domain.Review
import kr.co.fastcampus.eatgo.domain.ReviewRepository
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
    fun addReview() {
        val review = Review(name = "Yunseok", score = 3,
                description = "Mat-it-da")

        reviewService.addReview(1004, review)
        verify(reviewRepository).save(any())
    }
}
