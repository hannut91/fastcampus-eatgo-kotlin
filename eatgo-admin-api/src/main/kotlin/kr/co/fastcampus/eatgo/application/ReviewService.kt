package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.ReviewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReviewService(
        @Autowired private val reviewRepository: ReviewRepository
) {
    fun getReviews() = reviewRepository.findAll()
}
