package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.RegionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RegionService(
        @Autowired private val regionRepository: RegionRepository
) {
    fun getRegions() = regionRepository.findAll()
}
