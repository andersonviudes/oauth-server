package com.example.oauth.user.primary

import com.example.oauth.user.User


interface UpdateUserPort {
    fun update(user: User)
}
