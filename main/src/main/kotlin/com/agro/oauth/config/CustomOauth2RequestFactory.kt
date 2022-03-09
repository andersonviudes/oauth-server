package com.agro.oauth.config

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.TokenRequest
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory
import org.springframework.security.oauth2.provider.token.TokenStore

class CustomOauth2RequestFactory(
    private val clientDetailsService: ClientDetailsService,
    private val tokenStore: TokenStore,
    private val userDetailsService: UserDetailsService
) : DefaultOAuth2RequestFactory(clientDetailsService) {

    override fun createTokenRequest(
        requestParameters: Map<String?, String>,
        authenticatedClient: ClientDetails?
    ): TokenRequest {
        if (requestParameters["grant_type"] == "refresh_token") {
            val authentication: OAuth2Authentication = tokenStore.readAuthenticationForRefreshToken(
                tokenStore.readRefreshToken(requestParameters["refresh_token"])
            )
            SecurityContextHolder.getContext().authentication =
                UsernamePasswordAuthenticationToken(
                    authentication.name, null,
                    userDetailsService.loadUserByUsername(authentication.name).authorities
                )
        }
        return super.createTokenRequest(requestParameters, authenticatedClient)
    }
}