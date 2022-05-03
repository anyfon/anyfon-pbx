package ru.anyfon.pbx.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import ru.anyfon.web.auth.data.R2dbcUserRepository
import ru.anyfon.web.auth.domain.user.UserRepository

@Configuration
class WebAuthConfig {

    @Bean
    fun r2dbcUserRepository(entityTemplate: R2dbcEntityTemplate) : UserRepository =
        R2dbcUserRepository(entityTemplate)
}
