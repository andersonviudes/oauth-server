package com.example.oauth.membership.consumer.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.example.oauth.user.User
import java.time.LocalDateTime
import java.util.UUID

@JsonIgnoreProperties(ignoreUnknown = true)
data class MembershipAccountCreatedDTO(
    @JsonProperty
    val id: UUID,
    @JsonProperty
    val eventType: String,
    @JsonProperty
    val membershipId: UUID,
    @JsonProperty
    val person: PersonMessageDTO,
    @JsonProperty
    val business: BusinessMessageDTO,
    @JsonProperty
    val transactionPassword: TransactionPasswordMessageDTO,
    @JsonProperty
    val membershipType: String,
    @JsonProperty
    val email: String,
    @JsonProperty
    val modifiedAt: LocalDateTime? = null
) {

    fun toModel() = User(
        id = id,
        email = email,
        name = membershipId.toString(),
        pwd = person.accessPassword.password,
        enabled = true,
        accountNonLocked = false,
        accountNonExpired = false,
        credentialsNonExpired = false,
        roles = listOf()
    )

    fun toEventTypeDTO() = EventTypeDTO.from(eventType.uppercase())

    fun encodeAccessPassword(encodedAccessPasswordMessageDTO: AccessPasswordMessageDTO) =
        copy(person = person.copy(accessPassword = encodedAccessPasswordMessageDTO))
}

enum class EventTypeDTO {
    CREATE,
    UPDATE,
    DELETE,
    UNKNOWN;

    companion object {
        fun from(value: String) = values().find {
            it.name == value
        } ?: UNKNOWN
    }
}
