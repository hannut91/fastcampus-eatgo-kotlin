package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.ReviewService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewController {
    @Autowired
    private lateinit var reviewService: ReviewService

    @GetMapping("/reviews")
    fun list() = reviewService.getReviews()
}
