package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryController {
    @Autowired
    private lateinit var categoryService: CategoryService

    @GetMapping("/categories")
    fun list() = categoryService.getCategories()
}
