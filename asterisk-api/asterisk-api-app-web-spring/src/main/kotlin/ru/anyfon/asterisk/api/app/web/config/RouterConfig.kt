package ru.anyfon.asterisk.api.app.web.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter
import ru.anyfon.asterisk.api.app.web.handler.CdrHandler
import ru.anyfon.asterisk.api.domain.cdr.CallRecordService

@Configuration
class RouterConfig(callRecordService: CallRecordService) {

    private val cdrHandler = CdrHandler(callRecordService)

    companion object {
        const val SEQUENCE_START_PARAM = "sequenceStart"

        const val FETCH_LAST_CDR_PATH = "/api/cdr/fetch-last/{$SEQUENCE_START_PARAM}"

        const val DETAIL_RECORD_ID_PARAM = "detailsRecordId"

        const val FETCH_RECORD_FILE_CDR_PATH = "/api/cdr/fetch-record-file/{$DETAIL_RECORD_ID_PARAM}"
    }

    @Bean
    fun routes() = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            GET(FETCH_LAST_CDR_PATH, cdrHandler::handleList)
            GET(FETCH_RECORD_FILE_CDR_PATH, cdrHandler::handleFile)
        }
    }
}
