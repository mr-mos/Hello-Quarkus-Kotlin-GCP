package mos.example

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Entity

@Entity
class MyDomain : PanacheEntity() {
    companion object: PanacheCompanion<MyDomain> {
        fun findByName(name: String) = find("name", name).firstResult()
        fun all() = findAll()
    }

    lateinit var name: String

}