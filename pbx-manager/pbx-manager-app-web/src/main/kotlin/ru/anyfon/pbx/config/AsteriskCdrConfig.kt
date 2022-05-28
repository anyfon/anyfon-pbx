package ru.anyfon.pbx.config

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactoryOptions.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.anyfon.asterisk.api.data.cdr.R2DbcCallDetailsRepository
import ru.anyfon.asterisk.api.data.cdr.R2dbcChannelEventRepository
import ru.anyfon.asterisk.api.domain.cdr.CallRecordService
import ru.anyfon.asterisk.api.domain.cdr.CallRecordServiceImpl
import ru.anyfon.pbx.astersikcdr.AsteriskCdrHandlerImpl
import ru.anyfon.pbx.astersikcdr.AsteriskCdrLoader
import ru.anyfon.pbx.astersikcdr.AsteriskCdrLoaderImpl
import ru.anyfon.pbx.manager.domain.callmanager.cdr.descriptor.CallRecordDescriptor
import java.time.Duration


@Configuration
class AsteriskCdrConfig(
    @Value("\${anyfon.asterisk.mysql.host}") host: String,
    @Value("\${anyfon.asterisk.mysql.port}") port: Int,
    @Value("\${anyfon.asterisk.mysql.username}") username: String,
    @Value("\${anyfon.asterisk.mysql.password}") password: String,
    @Value("\${anyfon.asterisk.mysql.dbname}") dbname: String
) {

    companion object {
        const val LOADING_LIMIT = 1000
    }

    private val entityTemplate: R2dbcEntityTemplate

    init {
        val options = ConnectionFactoryOptions.builder()
            .option(DRIVER, "mysql")
            .option(HOST, host)
            .option(USER, username)
            .option(PASSWORD, password)
            .option(PORT, port)
            .option(DATABASE, dbname)
            .option(CONNECT_TIMEOUT, Duration.ofSeconds(3))
            .build()

        val connectionFactory = ConnectionFactories.get(options)

        entityTemplate = R2dbcEntityTemplate(connectionFactory)
    }

    @Bean
    fun asteriskCdrService() : CallRecordService = CallRecordServiceImpl(
        R2DbcCallDetailsRepository(entityTemplate),
        R2dbcChannelEventRepository(entityTemplate)
    )


    @Bean
    fun asteriskCdrLoader(
        callRecordDescriptor: CallRecordDescriptor,
        asteriskCdrService: CallRecordService
    ): AsteriskCdrLoader {
        return AsteriskCdrLoaderImpl(asteriskCdrService, AsteriskCdrHandlerImpl(callRecordDescriptor))
    }

    @Component
    class ScheduledAsteriskCdrLoader(
        private val asteriskCdrLoader: AsteriskCdrLoader
    ) {

        @Scheduled(fixedRate = 9_000)
        fun loadAsteriskCdr() = asteriskCdrLoader.load(LOADING_LIMIT)
    }

}
