package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.CategoryService
import kr.co.fastcampus.eatgo.domain.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class CategoryController {
    @Autowired
    private lateinit var categoryService: CategoryService

    @GetMapping("/categories")
    fun list() = categoryService.getCategories()

    @PostMapping("/categories")
    fun create(
            @RequestBody resource: Category
    ): ResponseEntity<Any> {
        val name = resource.name

        val category = categoryService.addCategory(name)

        val url = "/categories/${category.id}"
        return ResponseEntity.created(URI(url)).body("{}")
    }
}
