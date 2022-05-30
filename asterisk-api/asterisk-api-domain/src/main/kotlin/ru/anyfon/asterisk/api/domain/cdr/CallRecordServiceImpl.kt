package ru.anyfon.asterisk.api.domain.cdr

import ru.anyfon.common.util.ConvertUtils
import java.io.File

class CallRecordServiceImpl(
    private val recordRepository: CallDetailsRepository,
    private val eventRepository: ChannelEventRepository,
    private val callRecordFactory: CallRecordFactory = CallRecordFactory.Base()
) : CallRecordService {

    override suspend fun fetchLastEndedRecords(lastEndedCallEventId: String, limit: Int): List<CallRecord> {

        val eventId = ConvertUtils.toNumber(lastEndedCallEventId)?.toInt() ?: return emptyList()

        val calls = recordRepository.findLastEndedCallDetails(eventId, limit)

        val detailRecordMap = createDetailRecordMap(calls)

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

    override suspend fun findRecordingFile(id: CallDetails.ID): File? {

        val record = recordRepository.findById(id) ?: return null

        return File(composePath(record)).let {
            if (it.exists()) return@let it
            return@let null
        }
    }

    private fun composePath(callDetails: CallDetails): String {

        return File.separator +
                "var" + File.separator +
                "spool" + File.separator +
                "asterisk" + File.separator +
                "monitor" + File.separator +
                callDetails.callDateTime.year + File.separator +
                callDetails.callDateTime.monthValue.let {
                    if (it < 10) return@let "0".plus(it)
                    it.toString()
                } + File.separator +
                callDetails.callDateTime.dayOfMonth.let {
                    if (it < 10) return@let "0".plus(it)
                    it.toString()
                } + File.separator +
                callDetails.recordingFileName
    }

    override suspend fun removeRecordFile(id: CallDetails.ID) {
        TODO("Not yet implemented")
    }

}
