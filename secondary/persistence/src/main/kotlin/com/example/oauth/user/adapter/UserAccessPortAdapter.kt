package com.example.oauth.user.adapter

import com.example.oauth.user.User
import com.example.oauth.user.entity.toEntity
import com.example.oauth.user.repository.UserRepository
import com.example.oauth.user.secondary.UserAccessPort
import java.util.UUID
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class UserAccessPortAdapter(

    private val userRepository: UserRepository,

    ) : UserAccessPort {

    override fun findByName(username: String): User? {
        return userRepository.findByName(username)?.toModel()
    }

    override fun save(user: User): User {
        return userRepository.saveAndFlush(user.toEntity()).toModel()
    }

    override fun findAll(): List<User> {
        return userRepository.findAll().map { it.toModel() }
    }

    override fun findById(id: String): User? {
        return userRepository.findByIdOrNull(UUID.fromString(id))?.toModel()
    }

}
