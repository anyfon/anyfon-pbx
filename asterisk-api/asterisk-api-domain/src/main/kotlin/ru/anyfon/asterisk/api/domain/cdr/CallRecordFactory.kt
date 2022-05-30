package ru.anyfon.asterisk.api.domain.cdr

interface CallRecordFactory {
    fun create(detailRecords: List<CallDetails>, events: List<ChannelEvent>): CallRecord?

    class Base : CallRecordFactory {
        override fun create(detailRecords: List<CallDetails>, events: List<ChannelEvent>): CallRecord? {
            if (!isEndedCall(events)) return null
            val rootRecord = extractRootRecord(detailRecords) ?: return null
            return CallRecord(
                rootRecord.callRecordId,
                detailRecords,
                events
            )
        }

        private fun isEndedCall(events: List<ChannelEvent>): Boolean =
            events.any { it.eventType == ChannelEvent.Type.LINKEDID_END }

        private fun extractRootRecord(detailRecords: List<CallDetails>) : CallDetails? =
            detailRecords.firstOrNull {
                it.callRecordId.toString() == it.id.toString()
            }
    }
}
