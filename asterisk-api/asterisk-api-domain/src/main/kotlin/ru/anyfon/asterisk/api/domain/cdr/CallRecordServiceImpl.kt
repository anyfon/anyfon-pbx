package ru.anyfon.asterisk.api.domain.cdr

class CallRecordServiceImpl(
    private val recordRepository: CallDetailsRepository,
    private val eventRepository: ChannelEventRepository,
    private val callRecordFactory: CallRecordFactory = CallRecordFactory.Base()
) : CallRecordService {

    override suspend fun fetchLastEndedRecords(lastLinkedId: String, limit: Int): List<CallRecord> {
        val detailRecordMap = createDetailRecordMap(recordRepository.findAll(lastLinkedId, limit))
        val eventMap = createEventMap(eventRepository.findByCallRecordId(detailRecordMap.keys))

        return detailRecordMap.keys.mapNotNull {
            callRecordFactory.create(detailRecordMap[it] ?: emptyList(), eventMap[it] ?: emptyList())
        }
    }

    private fun createDetailRecordMap(detailRecords: List<CallDetails>): Map<CallRecord.ID, List<CallDetails>> {

        val detailRecordMap = mutableMapOf<CallRecord.ID, MutableList<CallDetails>>()

        detailRecords.forEach { record ->
            val currentList = detailRecordMap[record.callRecordId] ?: mutableListOf<CallDetails>().let {
                detailRecordMap[record.callRecordId] = it
                it
            }
            currentList.add(record)
        }

        return detailRecordMap
    }

    private fun createEventMap(events: List<ChannelEvent>): Map<CallRecord.ID, List<ChannelEvent>> {

        val eventMap = mutableMapOf<CallRecord.ID, MutableList<ChannelEvent>>()

        events.forEach { event ->
            val currentList = eventMap[event.callRecordId] ?: mutableListOf<ChannelEvent>().let {
                eventMap[event.callRecordId] = it
                it
            }
            currentList.add(event)
        }
        return eventMap
    }

    override suspend fun fetchRecordFile(id: CallDetails.ID): ByteArray? {
        TODO("Not yet implemented")
    }

    override suspend fun removeRecordFile(id: CallDetails.ID) {
        TODO("Not yet implemented")
    }

}
