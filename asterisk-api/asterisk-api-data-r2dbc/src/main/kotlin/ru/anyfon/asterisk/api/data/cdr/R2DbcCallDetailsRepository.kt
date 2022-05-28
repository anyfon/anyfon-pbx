package ru.anyfon.asterisk.api.data.cdr

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.domain.Sort
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import ru.anyfon.asterisk.api.domain.cdr.CallDetails
import ru.anyfon.asterisk.api.domain.cdr.CallDetailsRepository

class R2DbcCallDetailsRepository(
    private val entityTemplate: R2dbcEntityTemplate
) :CallDetailsRepository {
    override suspend fun findAll(lastLinkedId: String, limit: Int): List<CallDetails> {
        val query = Query.query(
            Criteria.where("linkedid").greaterThan(lastLinkedId)
        ).sort(Sort.by("uniqueid")).limit(limit)

        return entityTemplate
            .select(query, DetailRecordEntity::class.java)
            .map {
                it.toDetailRecord()
            }.collectList().awaitSingle()
    }
}
