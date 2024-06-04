package com.example.oauth.user.controller

import com.example.oauth.user.User
import com.example.oauth.user.dto.UserDTO
import com.example.oauth.user.primary.CreateUserPrimaryPort
import java.security.Principal
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(
    private val createUserPrimaryPort: CreateUserPrimaryPort
) {

    @ResponseStatus(OK)
    @GetMapping
    fun getUsers(): List<User> {
        return createUserPrimaryPort.findAll()
    }

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    fun getUserById(@PathVariable(value = "id") id: String): User? {
        return createUserPrimaryPort.findById(id)
    }

    @ResponseStatus(CREATED)
    @PostMapping
    fun creatUser(@RequestBody user: UserDTO) {
        createUserPrimaryPort.createUser(user.toModel())
    }

    @GetMapping("info")
    fun userinfo(principal: Principal?): Map<String, Any> {
        return principal?.let {
            mapOf(
                "name" to it.name,
                "authorities" to SecurityContextHolder.getContext().authentication.authorities
            )
        } ?: mapOf()
    }

}
