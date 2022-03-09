package com.agro.oauth.adapter

import com.agro.oauth.model.User
import com.agro.oauth.repository.UserRepository
import com.agro.oauth.service.secodary.UserAccessPort
import org.springframework.stereotype.Component

@Component
class UserAccessPortAdapter(
    private val userRepository: UserRepository
) : UserAccessPort {
    override fun findByName(username: String): User? {
        return userRepository.findByName(username)?.toModel()
    }
}