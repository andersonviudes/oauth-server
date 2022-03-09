package com.agro.oauth.config

import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.OAuth2RequestFactory
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory

@Configuration
@EnableAuthorizationServer
class OAuth2Configuration(
    val dataSource: DataSource,
    val passwordEncoder: PasswordEncoder,
    val clientDetailsService: ClientDetailsService,
    val authenticationManager: AuthenticationManager,
    val userDetailsService: UserDetailsService
) : AuthorizationServerConfigurerAdapter() {

    @Value("\${check-user-scopes}")
    private var checkUserScopes: Boolean = false

    @Bean
    fun requestFactory(): OAuth2RequestFactory {
        val requestFactory: CustomOauth2RequestFactory =
            CustomOauth2RequestFactory(clientDetailsService,tokenStore(),userDetailsService)
        requestFactory.setCheckUserScopes(true)
        return requestFactory
    }

    @Bean
    fun tokenStore(): TokenStore {
        return JwtTokenStore(jwtAccessTokenConverter())
    }

    @Bean
    fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
        val converter: JwtAccessTokenConverter = CustomTokenEnhancer()
        converter.setKeyPair(
            KeyStoreKeyFactory(
                ClassPathResource("jwt.jks"),
                "password".toCharArray()
            ).getKeyPair("jwt")
        )
        return converter
    }

    @Bean
    fun tokenEndpointAuthenticationFilter(): TokenEndpointAuthenticationFilter {
        return TokenEndpointAuthenticationFilter(authenticationManager, requestFactory())
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder)
    }

    override fun configure(oauthServer: AuthorizationServerSecurityConfigurer) {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.tokenStore(tokenStore()).tokenEnhancer(jwtAccessTokenConverter())
            .authenticationManager(authenticationManager).userDetailsService(userDetailsService)
        if (checkUserScopes) endpoints.requestFactory(requestFactory())
    }
}