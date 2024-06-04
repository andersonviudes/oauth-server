package com.example.oauth.membership.consumer.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class PersonalDocumentMessageDTO(
    @JsonProperty
    val frontUrl: String,
    @JsonProperty
    val backUrl: String,
    @JsonProperty
    val documentType: String,
    @JsonProperty
    val modifiedAt: LocalDateTime? = null
)
