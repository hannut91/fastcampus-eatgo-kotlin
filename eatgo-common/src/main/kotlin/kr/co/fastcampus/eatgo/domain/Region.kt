package kr.co.fastcampus.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Region(
        @Id
        @GeneratedValue
        var id: Long = 0,

        var name: String = ""
)
