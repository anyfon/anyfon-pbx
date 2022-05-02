package ru.anyfon.pbx.manager.domain.calldetails

interface CallDetailsService {
    suspend fun findById(rootId: CallId): CallDetails?
    suspend fun findAll(): List<CallDetails>
}
