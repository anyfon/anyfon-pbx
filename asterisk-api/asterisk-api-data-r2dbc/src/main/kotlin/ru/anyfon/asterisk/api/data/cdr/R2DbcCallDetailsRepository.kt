package ru.anyfon.asterisk.api.data.cdr

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.domain.Sort
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import ru.anyfon.asterisk.api.domain.cdr.CallDetails
import ru.anyfon.asterisk.api.domain.cdr.CallDetailsRepository
import ru.anyfon.asterisk.api.domain.cdr.ChannelEvent

class R2DbcCallDetailsRepository(
    private val entityTemplate: R2dbcEntityTemplate
) : CallDetailsRepository {
    override suspend fun findLastEndedCallDetails(lastEventId: Int, limit: Int): List<CallDetails> {
        val query = Query.query(
            Criteria.where("eventtype").`is`(ChannelEvent.Type.LINKEDID_END).and(
                Criteria.where("id").greaterThan(lastEventId)
            )
        ).limit(limit)

        return entityTemplate.select(query, ChannelEventEntity::class.java).map {
            it.linkedId
        }.collectList().flatMap { ids ->
            val recordQuery = Query.query(
                Criteria.where("linkedId").`in`(ids)
            ).sort(Sort.by("calldate", "sequence"))
            entityTemplate.select(recordQuery, DetailRecordEntity::class.java).map { record ->
                record.toCallDetails()
            }.collectList()
        }.awaitSingle()

    }

    override suspend fun findById(id: CallDetails.ID): CallDetails? {
        val query = Query.query(
            Criteria.where("uniqueid").`is`(id.toString())
        )

        return entityTemplate
            .select(query, DetailRecordEntity::class.java).collectList()
            .awaitSingle().sortedBy {
                it.duration
            }
            .lastOrNull()?.toCallDetails()
    }
}
