package com.example.oauth.user.secondary

import com.example.oauth.user.Role

interface RoleAccessPort {
    fun findRoleByName(name: String): Role?
}
