package com.agro.oauth.config

import com.agro.oauth.model.User
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

class CustomTokenEnhancer : JwtAccessTokenConverter() {

    override fun enhance(accessToken: OAuth2AccessToken, authentication: OAuth2Authentication): OAuth2AccessToken {
        val user: User = authentication.principal as User
        val info: MutableMap<String, Any> = LinkedHashMap<String, Any>(accessToken.getAdditionalInformation())
        info["email"] = user.email
        val customAccessToken = DefaultOAuth2AccessToken(accessToken)
        customAccessToken.additionalInformation = info
        return super.enhance(customAccessToken, authentication)
    }

}

