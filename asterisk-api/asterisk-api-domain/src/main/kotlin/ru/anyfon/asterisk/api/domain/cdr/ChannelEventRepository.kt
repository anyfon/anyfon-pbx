package ru.anyfon.asterisk.api.domain.cdr

interface ChannelEventRepository {

    suspend fun findByCallRecordId(vararg id: CallRecord.ID) : List<ChannelEvent> = findByCallRecordId(id.toList())

    suspend fun findByCallRecordId(ids: Iterable<CallRecord.ID>) : List<ChannelEvent>

}
