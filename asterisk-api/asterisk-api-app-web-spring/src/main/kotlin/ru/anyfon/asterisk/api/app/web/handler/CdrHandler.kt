package ru.anyfon.asterisk.api.app.web.handler

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.HttpHeaders
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import ru.anyfon.asterisk.api.app.web.config.RouterConfig
import ru.anyfon.asterisk.api.domain.cdr.CallRecordService
import ru.anyfon.asterisk.api.domain.cdr.CallDetails
import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.common.util.toNumber

class CdrHandler(
    private val service: CallRecordService
) {

    suspend fun handleList(request: ServerRequest): ServerResponse {

        val sequenceStart = ConvertUtils.tryOrNull {
            request.pathVariable(RouterConfig.SEQUENCE_START_PARAM)
        }?.toNumber()?.toInt() ?: Int.MAX_VALUE

        val cdrList = service.fetchLastEndedRecords(sequenceStart)

        return ServerResponse
            .status(200)
            .bodyValueAndAwait(cdrList)

    }

    suspend fun handleFile(request: ServerRequest): ServerResponse {

        val detailRecordId = ConvertUtils.tryOrNull {
            val id = request.pathVariable(RouterConfig.DETAIL_RECORD_ID_PARAM)
            CallDetails.ID(id)
        } ?: return ServerResponse.badRequest().bodyValue("Bad detail record ID").awaitSingle()

        return service.fetchRecordFile(detailRecordId)?.let {
            ServerResponse.status(200)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=call-record.wav")
                .header(HttpHeaders.CONTENT_TYPE, "audio/vnd.wave")
                .bodyValue(it).awaitSingle()
        } ?: ServerResponse.notFound().buildAndAwait()
    }
}
