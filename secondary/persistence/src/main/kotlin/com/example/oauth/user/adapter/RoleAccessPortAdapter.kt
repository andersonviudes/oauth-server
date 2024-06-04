package com.example.oauth.user.adapter

import com.example.oauth.user.Role
import com.example.oauth.user.repository.RoleRepository
import com.example.oauth.user.secondary.RoleAccessPort
import org.springframework.stereotype.Component

@Component
class RoleAccessPortAdapter(

    private val roleRepository: RoleRepository

) : RoleAccessPort {

    override fun findRoleByName(name: String): Role? {
        return roleRepository.findByName(name)?.toModel()
    }

}
