package com.agro.oauth.model

import java.util.UUID
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class User(

    val id: UUID,

    val email: String,

    val name: String,

    val pwd: String,

    val enabled: Boolean = false,

    val accountNonLocked: Boolean,

    val accountNonExpired: Boolean,

    val credentialsNonExpired: Boolean,

    val roles: List<Role>

) : UserDetails {
    override fun isEnabled(): Boolean {
        return enabled
    }

    override fun isAccountNonExpired(): Boolean {
        return !accountNonExpired
    }

    override fun isCredentialsNonExpired(): Boolean {
        return !credentialsNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return !accountNonLocked
    }

    override fun getAuthorities(): MutableSet<out GrantedAuthority> {
        val authorities = mutableSetOf<GrantedAuthority>()
        roles.forEach { role ->
            authorities.add(SimpleGrantedAuthority(role.name))
            role.permissions.forEach { permission ->
                authorities.add(SimpleGrantedAuthority(permission.name))
            }
        }
        return authorities
    }

    override fun getPassword(): String {
        return pwd
    }

    override fun getUsername(): String {
        return name
    }
}
