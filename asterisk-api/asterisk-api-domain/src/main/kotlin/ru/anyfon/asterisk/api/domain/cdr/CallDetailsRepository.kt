package ru.anyfon.asterisk.api.domain.cdr

interface CallDetailsRepository {
    suspend fun findLastEndedCallDetails(lastEventId: Int, limit: Int = 100) : List<CallDetails>

    suspend fun findById(id: CallDetails.ID) : CallDetails?
}
