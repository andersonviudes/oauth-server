package com.example.oauth.config

import com.example.oauth.user.User
import java.util.Date
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

class CustomTokenEnhancer(val tokenExpiration: Int) : JwtAccessTokenConverter() {

    override fun enhance(accessToken: OAuth2AccessToken, authentication: OAuth2Authentication): OAuth2AccessToken {
        val user: User = authentication.principal as User
        val info: MutableMap<String, Any> = LinkedHashMap<String, Any>(accessToken.additionalInformation)
        info["email"] = user.email
        val customAccessToken = DefaultOAuth2AccessToken(accessToken)
        customAccessToken.additionalInformation = info
        val currentTimeMillis = System.currentTimeMillis()
        customAccessToken.expiration = Date(currentTimeMillis + tokenExpiration)
        return super.enhance(customAccessToken, authentication)
    }

}
