package ru.anyfon.asterisk.api.data.cdr

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.domain.Sort
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import reactor.core.publisher.Flux
import ru.anyfon.asterisk.api.domain.cdr.CallRecord
import ru.anyfon.asterisk.api.domain.cdr.ChannelEvent
import ru.anyfon.asterisk.api.domain.cdr.ChannelEventRepository

class R2dbcChannelEventRepository(
    private val entityTemplate: R2dbcEntityTemplate
) : ChannelEventRepository {

    override suspend fun findByCallRecordId(ids: Iterable<CallRecord.ID>): List<ChannelEvent> {
        val idList = ids.toList().map { it.toString() }
        if (idList.isEmpty()) return emptyList()

        val query = Query
            .query(Criteria.where("linkedid").`in`(idList))
            .sort(Sort.by("eventtime","id").ascending())

        return entityTemplate
            .select(query, ChannelEventEntity::class.java).map {
                it.toChannelEvent()
            }.collectList().awaitSingle()
    }
}
