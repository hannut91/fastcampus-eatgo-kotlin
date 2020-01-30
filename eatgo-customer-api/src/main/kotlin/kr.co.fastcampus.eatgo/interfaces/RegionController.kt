package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RegionService
import kr.co.fastcampus.eatgo.domain.Region
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RegionController {
    @Autowired
    private lateinit var regionService: RegionService

    @GetMapping("/regions")
    fun list(): List<Region> {
        val regions = regionService.getRegions()
        return regions
    }
}
