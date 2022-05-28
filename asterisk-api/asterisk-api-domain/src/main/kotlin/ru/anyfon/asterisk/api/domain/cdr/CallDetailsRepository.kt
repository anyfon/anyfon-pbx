package ru.anyfon.asterisk.api.domain.cdr

interface CallDetailsRepository {
    suspend fun findAll(lastLinkedId: String, limit: Int = 100) : List<CallDetails>
}
