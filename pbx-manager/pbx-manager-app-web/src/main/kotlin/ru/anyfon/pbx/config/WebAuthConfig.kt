package ru.anyfon.pbx.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import ru.anyfon.pbx.resource.tenant.TenantHandler
import ru.anyfon.pbx.resource.tenant.TenantHandlerImpl
import ru.anyfon.pbx.resource.user.UserHandler
import ru.anyfon.pbx.resource.user.UserHandlerImpl
import ru.anyfon.web.auth.data.R2dbcTenantRepository
import ru.anyfon.web.auth.data.R2dbcUserRepository
import ru.anyfon.web.auth.domain.tenant.TenantRepository
import ru.anyfon.web.auth.domain.user.UserRepository

@Configuration
class WebAuthConfig(
    private val entityTemplate: R2dbcEntityTemplate
) {

    @Bean
    fun userRepository(): UserRepository =
        R2dbcUserRepository(entityTemplate)

    @Bean
    fun tenantRepository(): TenantRepository =
        R2dbcTenantRepository(entityTemplate)

    @Bean
    fun userHandler(userRepository: UserRepository): UserHandler =
        UserHandlerImpl(userRepository)

    @Bean
    fun tenantHandler(tenantRepository: TenantRepository) : TenantHandler =
        TenantHandlerImpl(tenantRepository)

}
