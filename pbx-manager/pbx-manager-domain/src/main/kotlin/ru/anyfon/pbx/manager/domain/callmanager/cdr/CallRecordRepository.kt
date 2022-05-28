package ru.anyfon.pbx.manager.domain.callmanager.cdr

import ru.anyfon.pbx.manager.domain.callmanager.CallID

interface CallRecordRepository {

    suspend fun findByFilters(params: CallRecordParams.Filter): List<CallRecord>

    suspend fun findById(callID: CallID): CallRecord?

    suspend fun add(params: CallRecordParams.Data)
}
