package ru.anyfon.asterisk.api.domain.cdr

import java.io.File

interface CallRecordService {

    companion object {
        val BASE_RECORD_PATH = File.separator + "var" +
                File.separator + "spool" +
                File.separator + "asterisk" +
                File.separator + "monitor"
    }

    suspend fun fetchLastEndedRecords(lastEndedCallEventId: String, limit: Int = 100): List<CallRecord>

    suspend fun findRecordingFile(id: CallDetails.ID): File?

    suspend fun removeRecordFile(id: CallDetails.ID)
}
