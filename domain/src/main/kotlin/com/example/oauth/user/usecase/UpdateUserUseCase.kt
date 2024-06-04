package com.example.oauth.user.usecase

import com.example.oauth.user.User
import com.example.oauth.user.primary.UpdateUserPort
import com.example.oauth.user.secondary.UserAccessPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class UpdateUserUseCase(
    private val userAccessPort: UserAccessPort
) : UpdateUserPort {
    override fun update(user: User) {
        userAccessPort.findByName(user.username)?.let {
            val updatedUser = it.copy(
                pwd = user.pwd,
                email = user.email
            )

            userAccessPort.save(updatedUser)

            logger.info("Updated User=${user.id}")
        } ?: logger.error("User with username=${user.id} not found!")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
