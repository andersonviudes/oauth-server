package com.agro.oauth.entity

import com.agro.oauth.model.Role
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import org.hibernate.annotations.GenericGenerator

@Entity(name = "role")
data class RoleEntity(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    val id: UUID,

    var name: String? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "permission_role",
        joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id", referencedColumnName = "id")]
    )
    val permissions: List<PermissionEntity>
) {
    fun toModel() = Role(
        id = id,
        name = name,
        permissions = permissions.map { it.toModel() }
    )
}