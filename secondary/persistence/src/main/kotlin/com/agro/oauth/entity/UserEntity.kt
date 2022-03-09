package com.agro.oauth.entity

import com.agro.oauth.model.User
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table
import org.hibernate.annotations.GenericGenerator

@Entity(name = "user")
@Table(name = "\"user\"")
data class UserEntity(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    val id: UUID,
    val email: String,
    val name: String,
    val pwd: String,
    val enabled: Boolean = false,

    @Column(name = "account_locked")
    private val accountNonLocked: Boolean,

    @Column(name = "account_expired")
    private val accountNonExpired: Boolean,

    @Column(name = "credentials_expired")
    private val credentialsNonExpired: Boolean,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_user",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    private val roles: List<RoleEntity>
) {

    fun toModel() = User(
        id = id,
        email = email,
        name = name,
        pwd = pwd,
        enabled = enabled,
        accountNonLocked = accountNonLocked,
        accountNonExpired = accountNonExpired,
        credentialsNonExpired = credentialsNonExpired,
        roles = roles.map { it.toModel() }
    )
}