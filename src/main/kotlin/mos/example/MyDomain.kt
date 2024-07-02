package mos.example

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class MyDomain : PanacheEntityBase {
    companion object: PanacheCompanion<MyDomain> {
        fun findByName(name: String) = find("name", name).firstResult()
        fun all() = findAll()
    }


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: Long? = null

    lateinit var name: String

}