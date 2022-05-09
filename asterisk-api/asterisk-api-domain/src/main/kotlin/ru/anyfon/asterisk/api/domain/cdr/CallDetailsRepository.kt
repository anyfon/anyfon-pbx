package ru.anyfon.asterisk.api.domain.cdr

interface CallDetailsRepository {
    suspend fun findAll(sequenceStart: Int) : List<CallDetails>
}
