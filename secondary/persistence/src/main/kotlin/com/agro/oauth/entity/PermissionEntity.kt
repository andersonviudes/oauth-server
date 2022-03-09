package com.agro.oauth.entity

import com.agro.oauth.model.Permission
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import org.hibernate.annotations.GenericGenerator

@Entity(name = "permission")
data class PermissionEntity(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    val id: UUID,

    val name: String? = null
) {
    fun toModel() = Permission(
        id = id, name = name

    )
}