package com.example.oauth.membership.consumer.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class AddressMessageDTO(
    @JsonProperty
    val street: String,
    @JsonProperty
    val number: String?,
    @JsonProperty
    val complement: String?,
    @JsonProperty
    val district: String?,
    @JsonProperty
    val city: String,
    @JsonProperty
    val state: String,
    @JsonProperty
    val country: String = "Brasil",
    @JsonProperty
    val zipCode: String?,
    @JsonProperty
    val modifiedAt: LocalDateTime? = null
)
