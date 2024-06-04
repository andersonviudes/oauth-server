package com.example.oauth.membership.consumer.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.util.UUID

@JsonIgnoreProperties(ignoreUnknown = true)
data class CompanyMessageDTO(
    @JsonProperty
    val id: UUID,
    @JsonProperty
    val nationalRegistration: String,
    @JsonProperty
    val country: String,
    @JsonProperty
    val modifiedAt: LocalDateTime? = null
)
