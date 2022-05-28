package ru.anyfon.pbx.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import ru.anyfon.pbx.manager.data.callmanager.cdr.R2dbcCallRecordRepository
import ru.anyfon.pbx.manager.domain.callmanager.cdr.CallRecordRepository
import ru.anyfon.pbx.manager.domain.callmanager.cdr.descriptor.CallRecordDescriptor
import ru.anyfon.pbx.manager.domain.callmanager.cdr.descriptor.CallRecordDescriptorImpl

@Configuration
class PbxManagerConfig {

    @Bean
    fun callRecordRepository(
        objectMapper: ObjectMapper,
        entityTemplate: R2dbcEntityTemplate
    ) : CallRecordRepository = R2dbcCallRecordRepository(entityTemplate, objectMapper)

    @Bean
    fun callRecordDescriptor(repository: CallRecordRepository) : CallRecordDescriptor =
        CallRecordDescriptorImpl(repository)


}
