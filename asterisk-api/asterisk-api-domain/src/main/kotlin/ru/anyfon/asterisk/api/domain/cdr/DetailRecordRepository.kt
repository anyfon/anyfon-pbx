package ru.anyfon.asterisk.api.domain.cdr

interface DetailRecordRepository {
    suspend fun findAll(sequenceStart: Int) : List<DetailRecord>
}
