package kr.co.fastcampus.eatgo.domain

import org.springframework.data.repository.CrudRepository

interface RegionRepository : CrudRepository<Region, Long> {
    override fun findAll(): List<Region>

    override fun <S : Region> save(entity: S): S
}
