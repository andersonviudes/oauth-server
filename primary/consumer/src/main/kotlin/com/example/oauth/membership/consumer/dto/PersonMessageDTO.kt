package com.example.oauth.membership.consumer.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.util.UUID

@JsonIgnoreProperties(ignoreUnknown = true)
data class PersonMessageDTO(
    @JsonProperty
    val id: UUID,
    @JsonProperty
    val fullName: String,
    @JsonProperty
    val nationalRegistration: String,
    @JsonProperty
    val address: AddressMessageDTO,
    @JsonProperty
    val accessPassword: AccessPasswordMessageDTO,
    @JsonProperty
    val modifiedAt: LocalDateTime? = null
)
