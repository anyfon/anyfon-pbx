package ru.anyfon.web.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.ecom24.platform.serializer.StringValueObjectSerializer
import org.springframework.context.annotation.Configuration
import ru.anyfon.web.serializer.NumberValueObjectSerializer

class AppConfig(objectMapper: ObjectMapper) {
    init {
        val module = SimpleModule()

        module.addSerializer(StringValueObjectSerializer())
        module.addSerializer(NumberValueObjectSerializer())
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY)

        objectMapper.registerModule(module)
    }
}
