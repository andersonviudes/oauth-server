package com.example.oauth.membership.consumer.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class TransactionPasswordMessageDTO(
    @JsonProperty
    val password: String,
    @JsonProperty
    val salt: String,
    @JsonProperty
    val modifiedAt: LocalDateTime? = null
)
