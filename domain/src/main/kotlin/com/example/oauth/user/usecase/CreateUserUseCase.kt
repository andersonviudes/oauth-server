package com.example.oauth.user.usecase

import com.example.oauth.user.User
import com.example.oauth.user.primary.CreateUserPrimaryPort
import com.example.oauth.user.secondary.RoleAccessPort
import com.example.oauth.user.secondary.UserAccessPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateUserUseCase(

    private val userAccessPort: UserAccessPort,

    private val roleAccessPort: RoleAccessPort

) : CreateUserPrimaryPort {

    @Transactional
    override fun createUser(user: User): User {
        logger.info("createUser= $user")
        val role = roleAccessPort.findRoleByName("owner")!!

        val roles = user.roles.plus(role)

        val userCreated = userAccessPort.save(user.copy(roles = roles))
        logger.info("saved user= $roles")
        return userCreated
    }

    override fun findAll(): List<User> {
        return userAccessPort.findAll()
    }

    override fun findById(id: String): User? {
        return userAccessPort.findById(id)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
