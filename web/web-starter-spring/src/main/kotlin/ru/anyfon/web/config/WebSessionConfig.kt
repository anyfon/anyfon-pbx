package ru.anyfon.web.config

import org.springframework.context.annotation.Bean
import org.springframework.web.server.session.DefaultWebSessionManager
import org.springframework.web.server.session.WebSessionIdResolver
import org.springframework.web.server.session.WebSessionManager

class WebSessionConfig {
//    @Bean
//    fun webSessionIdResolver(jwtTokenService: JwtTokenService?): WebSessionIdResolver? {
//        return CookieAndJwtWebSessionIdResolver(jwtTokenService)
//    }

    @Bean
    fun webSessionManager(resolver: WebSessionIdResolver?): WebSessionManager? {
        val manager = DefaultWebSessionManager()
        manager.sessionIdResolver = resolver
        return manager
    }
}
