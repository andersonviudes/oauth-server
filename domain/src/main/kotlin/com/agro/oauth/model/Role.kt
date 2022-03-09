package com.agro.oauth.model

import java.util.UUID

data class Role(
    val id: UUID,

    var name: String? = null,

    val permissions: List<Permission>
)