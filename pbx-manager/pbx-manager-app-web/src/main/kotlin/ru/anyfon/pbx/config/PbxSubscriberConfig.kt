package ru.anyfon.pbx.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.security.crypto.password.PasswordEncoder
import ru.anyfon.pbx.manager.data.callmanager.subscriber.R2dbcSubscriberRepository
import ru.anyfon.pbx.manager.domain.subscriber.SubscriberRepository
import ru.anyfon.pbx.manager.domain.subscriber.service.SubscriberService
import ru.anyfon.pbx.manager.domain.subscriber.service.SubscriberServiceImpl

@Configuration
class PbxSubscriberConfig {

    @Bean
    protected fun sipSubscriberRepository(
        entityTemplate: R2dbcEntityTemplate
    ): SubscriberRepository = R2dbcSubscriberRepository(entityTemplate)

    @Bean
    protected fun sipSubscriberService(
        subscriberRepository: R2dbcSubscriberRepository,
        passwordEncoder: PasswordEncoder
    ): SubscriberService = SubscriberServiceImpl(subscriberRepository) {
        passwordEncoder.encode(it)
    }

}
