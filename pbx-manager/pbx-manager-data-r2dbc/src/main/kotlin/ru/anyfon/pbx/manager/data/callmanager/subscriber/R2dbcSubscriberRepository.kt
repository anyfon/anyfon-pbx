package ru.anyfon.pbx.manager.data.callmanager.subscriber

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import ru.anyfon.pbx.common.domain.service.request.Request
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber
import ru.anyfon.pbx.manager.domain.subscriber.SubscriberRepository


open class R2dbcSubscriberRepository(
    private val entityTemplate: R2dbcEntityTemplate
) : SubscriberRepository {

    private companion object {
        const val ID_COLUMN = "id"
    }

    override suspend fun findAllById(
        uuids: Request.Valid<Iterable<Subscriber.Uuid>>
    ): List<Subscriber> = Query.query(
        Criteria.where(ID_COLUMN).`in`(uuids.value.map { it.value })
    ).let { query ->
        entityTemplate.select(query, SubscriberEntity::class.java).map {
            it.toSubscriber()
        }.collectList().awaitSingle()
    }

    override suspend fun findById(
        uuid: Request.Valid<Subscriber.Uuid>
    ): Subscriber? = entityTemplate
        .selectOne(composeIdQuery(uuid.value), SubscriberEntity::class.java)
        .map { it.toSubscriber() }.awaitSingle()

    override suspend fun deleteAllById(
        uuids: Request.Valid<Iterable<Subscriber.Uuid>>
    ) = Query.query(
        Criteria.where(ID_COLUMN).`in`(uuids.value.map { it.toString() })
    ).let { query ->
        entityTemplate.delete(query, SubscriberEntity::class.java).awaitSingle()
        return@let
    }


    override suspend fun add(
        username: Request.Valid<Subscriber.Username>,
        enabled: Boolean,
        passwordHash: String?
    ): Subscriber.Uuid = SubscriberEntity(
        username.value.toString(),
        enabled, passwordHash
    ).let { entity ->
        entityTemplate
            .insert(entity)
            .awaitSingle()
            .let { Subscriber.Uuid(it.id) }
    }

    override suspend fun update(
        subscriberUuid: Request.Valid<Subscriber.Uuid>,
        username: Request.Valid<Subscriber.Username>?,
        enabled: Boolean?,
        passwordHash: String?
    ) {
        entityTemplate.selectOne(
            composeIdQuery(subscriberUuid.value),
            SubscriberEntity::class.java
        ).map {
            it.update(username?.value?.toString(), enabled, passwordHash)
        }.flatMap { updatedEntity ->
            entityTemplate.update(updatedEntity)
        }.awaitSingle()
    }

    private fun composeIdQuery(uuid: Subscriber.Uuid): Query =
        Query.query(Criteria.where(ID_COLUMN).`is`(uuid.toString()))
}
