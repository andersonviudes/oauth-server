package com.example.oauth.user.repository

import com.example.oauth.user.entity.UserEntity
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun findByName(name: String): UserEntity?
}
