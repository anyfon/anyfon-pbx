package ru.anyfon.asterisk.api.app.web.handler

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.HttpHeaders
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import ru.anyfon.asterisk.api.domain.cdr.CallDetails
import ru.anyfon.asterisk.api.domain.cdr.CallRecordService
import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.common.util.toNumber

class CdrHandler(
    private val service: CallRecordService
) {

    companion object {
        const val LAST_LINKEDID_PARAM = "lastLinkedId"

        const val LIMIT_PARAM = "limit"

        const val FETCH_LAST_CDR_PATH = "/api/cdr/fetch-last"

        const val DETAIL_RECORD_ID_PARAM = "detailsRecordId"

        const val FETCH_RECORD_FILE_CDR_PATH = "/api/cdr/fetch-record-file/{$DETAIL_RECORD_ID_PARAM}"
    }

    suspend fun handleList(request: ServerRequest): ServerResponse {

        val lastLinkedId = request.queryParam(LAST_LINKEDID_PARAM).orElse("0")

        val limit = request.queryParam(LIMIT_PARAM).map {
            it.toNumber()?.toInt()
        }.orElse(null) ?: 100

        val cdrList = service.fetchLastEndedRecords(lastLinkedId, limit)

        return ServerResponse
            .status(200)
            .bodyValueAndAwait(cdrList)

    }

    suspend fun handleFile(request: ServerRequest): ServerResponse {

        val detailRecordId = ConvertUtils.tryOrNull {
            val id = request.pathVariable(DETAIL_RECORD_ID_PARAM)
            CallDetails.ID(id)
        } ?: return ServerResponse.badRequest().bodyValue("Bad detail record ID").awaitSingle()

        return service.findRecordingFile(detailRecordId)?.let {
            ServerResponse.status(200)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=call-record.wav")
                .header(HttpHeaders.CONTENT_TYPE, "audio/vnd.wave")
                .bodyValue(it.readBytes()).awaitSingle()
        } ?: ServerResponse.notFound().buildAndAwait()
    }
}
