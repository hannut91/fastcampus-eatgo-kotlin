package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RegionService
import kr.co.fastcampus.eatgo.domain.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class RegionController {
    @Autowired
    private lateinit var regionService: RegionService

    @GetMapping("/regions")
    fun list() = regionService.getRegions()

    @PostMapping("/regions")
    fun create(
            @RequestBody resource: Category
    ): ResponseEntity<Any> {
        val name = resource.name

        val region = regionService.addRegion(name)

        val url = "/regions/${region.id}"
        return ResponseEntity.created(URI(url)).body("{}")
    }
}
