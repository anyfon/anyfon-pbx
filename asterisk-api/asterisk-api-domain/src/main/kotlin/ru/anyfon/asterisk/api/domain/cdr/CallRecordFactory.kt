package ru.anyfon.asterisk.api.domain.cdr

interface CallRecordFactory {
    fun create(detailRecords: List<DetailRecord>, events: List<ChannelEvent>): CallRecord?

    class Base : CallRecordFactory {
        override fun create(detailRecords: List<DetailRecord>, events: List<ChannelEvent>): CallRecord? {
            if (!isEndedCall(events)) return null
            val rootRecord = extractRootRecord(detailRecords) ?: return null
            return CallRecord(
                rootRecord.callRecordId,
                detailRecords,
                events,
                detailRecords.maxOf {
                    it.sequence
                }
            )
        }

        private fun isEndedCall(events: List<ChannelEvent>): Boolean =
            events.any { it.eventType == ChannelEvent.Type.LINKEDID_END }

        private fun extractRootRecord(detailRecords: List<DetailRecord>) : DetailRecord? =
            detailRecords.firstOrNull { it.callRecordId.equals(it.id) }
    }
}
