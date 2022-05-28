package ru.anyfon.asterisk.api.app.web.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter
import ru.anyfon.asterisk.api.app.web.handler.CdrHandler
import ru.anyfon.asterisk.api.app.web.handler.CdrHandler.Companion.FETCH_LAST_CDR_PATH
import ru.anyfon.asterisk.api.app.web.handler.CdrHandler.Companion.FETCH_RECORD_FILE_CDR_PATH
import ru.anyfon.asterisk.api.domain.cdr.CallRecordService

@Configuration
class RouterConfig(callRecordService: CallRecordService) {

    private val cdrHandler = CdrHandler(callRecordService)

    @Bean
    fun routes() = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            GET(FETCH_LAST_CDR_PATH, cdrHandler::handleList)
            GET(FETCH_RECORD_FILE_CDR_PATH, cdrHandler::handleFile)
        }
    }
}
