package com.example.oauth.user.primary

import com.example.oauth.user.User

interface CreateUserPrimaryPort {

    fun createUser(user: User): User
    fun findAll(): List<User>
    fun findById(id: String): User?

}
