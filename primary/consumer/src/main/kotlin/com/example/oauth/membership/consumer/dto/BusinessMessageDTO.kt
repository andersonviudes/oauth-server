package com.example.oauth.membership.consumer.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class BusinessMessageDTO(
    @JsonProperty
    val id: UUID,
    @JsonProperty
    val tradeName: String,
    @JsonProperty
    val companies: List<CompanyMessageDTO>
)
