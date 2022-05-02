package ru.anyfon.asterisk.api.app.web.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.ecom24.platform.serializer.StringValueObjectSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import ru.anyfon.asterisk.api.data.cdr.R2dbcChannelEventRepository
import ru.anyfon.asterisk.api.data.cdr.R2dbcDetailRecordRepository
import ru.anyfon.asterisk.api.domain.cdr.CallRecordService
import ru.anyfon.asterisk.api.domain.cdr.CallRecordServiceImpl
import ru.anyfon.web.serializer.NumberValueObjectSerializer

@Configuration
class AppConfig(
    objectMapper: ObjectMapper,
) {
    init {
        val module = SimpleModule()

        module.addSerializer(StringValueObjectSerializer())
        module.addSerializer(NumberValueObjectSerializer())
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY)

        objectMapper.registerModule(module)
    }

        @Bean
    fun callRecordService(entityTemplate: R2dbcEntityTemplate) : CallRecordService {
        val recordRepository = R2dbcDetailRecordRepository(entityTemplate)
        val eventRepository = R2dbcChannelEventRepository(entityTemplate)
        return CallRecordServiceImpl(recordRepository, eventRepository)
    }
}
