package com.example.oauth.user.secondary

import com.example.oauth.user.User

interface UserAccessPort {

    fun findByName(username: String): User?
    fun save(user: User): User
    fun findAll(): List<User>
    fun findById(id: String): User?

}
