package com.example.oauth.user.service

import com.example.oauth.user.secondary.UserAccessPort
import org.springframework.security.authentication.AccountStatusUserDetailsChecker
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userAccessPort: UserAccessPort
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val accountStatusUserDetailsChecker = AccountStatusUserDetailsChecker()
        val user = userAccessPort.findByName(username) ?: throw BadCredentialsException("Bad credentials")
        accountStatusUserDetailsChecker.check(user)
        return user
    }

}
