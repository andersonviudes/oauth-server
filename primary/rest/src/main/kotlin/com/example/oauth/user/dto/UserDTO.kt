package com.example.oauth.user.dto

import com.example.oauth.user.User
import java.util.UUID

data class UserDTO(
    val id: UUID,
    val name: String,
    val pass: String
) {

    fun toModel() = User(
        id = id,
        email = "staging@viudes.com.br",
        name = id.toString(),
        pwd = pass,
        enabled = true,
        accountNonLocked = false,
        accountNonExpired = false,
        credentialsNonExpired = false,
        roles = listOf()
    )

}
