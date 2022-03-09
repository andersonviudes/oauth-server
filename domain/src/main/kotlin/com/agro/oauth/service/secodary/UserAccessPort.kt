package com.agro.oauth.service.secodary

import com.agro.oauth.model.User

interface UserAccessPort {
    fun findByName(username: String): User?
}