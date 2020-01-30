package kr.co.fastcampus.eatgo.domain

import org.springframework.data.repository.CrudRepository

interface CategoryRepository : CrudRepository<Category, Long> {
    override fun findAll(): List<Category>

    override fun <S : Category> save(entity: S): S
}
