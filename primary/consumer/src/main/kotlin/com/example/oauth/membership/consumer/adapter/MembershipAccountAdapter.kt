package com.example.oauth.membership.consumer.adapter

import com.example.oauth.membership.consumer.dto.EventTypeDTO
import com.example.oauth.membership.consumer.dto.MembershipAccountCreatedDTO
import com.example.oauth.user.primary.CreateUserPrimaryPort
import com.example.oauth.user.primary.UpdateUserPort
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class MembershipAccountAdapter(
    private val createUserPrimaryPort: CreateUserPrimaryPort,
    private val updateUserPort: UpdateUserPort,
    private val passwordEncoder: PasswordEncoder
) {

    fun process(user: MembershipAccountCreatedDTO) {
        when (val eventType = user.toEventTypeDTO()) {
            EventTypeDTO.CREATE -> create(user)
            EventTypeDTO.UPDATE -> update(user)
            EventTypeDTO.DELETE -> logger.warn("DELETE method TO BE IMPLEMENTED")
            else -> logger.warn("Ignoring event type=$eventType")
        }
    }

    private fun create(membershipAccountCreatedDTO: MembershipAccountCreatedDTO) {
        val accessPasswordMessageDTO = encodeAccessPassword(membershipAccountCreatedDTO)

        createUserPrimaryPort.createUser(membershipAccountCreatedDTO.encodeAccessPassword(accessPasswordMessageDTO).toModel())
    }

    private fun update(membershipAccountCreatedDTO: MembershipAccountCreatedDTO) {
        val accessPasswordMessageDTO = encodeAccessPassword(membershipAccountCreatedDTO)

        updateUserPort.update(membershipAccountCreatedDTO.encodeAccessPassword(accessPasswordMessageDTO).toModel())
    }

    private fun encodeAccessPassword(membershipAccountCreatedDTO: MembershipAccountCreatedDTO) =
        membershipAccountCreatedDTO.person.accessPassword.copy(
            password = passwordEncoder.encode(
                membershipAccountCreatedDTO.person.accessPassword.password
            )
        )

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
