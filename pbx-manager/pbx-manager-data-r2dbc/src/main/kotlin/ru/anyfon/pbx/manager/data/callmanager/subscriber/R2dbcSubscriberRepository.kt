package ru.anyfon.pbx.manager.data.callmanager.subscriber

import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.CriteriaDefinition
import org.springframework.data.relational.core.query.Query
import org.springframework.transaction.annotation.Transactional
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber
import ru.anyfon.pbx.manager.domain.subscriber.SubscriberRepository


open class R2dbcSubscriberRepository(
    private val entityTemplate: R2dbcEntityTemplate
) : SubscriberRepository {

    private companion object {
        const val ID_COLUMN = "id"
    }

    override suspend fun findByFilters(): List<Subscriber> {

        val query = Query.query(CriteriaDefinition.empty())

        return entityTemplate.select(query, SubscriberEntity::class.java).map {
            it.toSubscriber()
        }.collectList().awaitSingle()
    }

    override suspend fun findById(uuid: Subscriber.Uuid): Subscriber? =
        entityTemplate.select(composeIdQuery(uuid), SubscriberEntity::class.java).map {
            it.toSubscriber()
        }.awaitSingle()

    @Transactional
    override suspend fun remove(uuid: Subscriber.Uuid) {
        entityTemplate.delete(composeIdQuery(uuid)).awaitSingle()
    }

    @Transactional
    override suspend fun save(subscriber: Subscriber, passwordHash: String?): Subscriber.Uuid {

        if (subscriber.uuid.isEmpty()) return insert(subscriber, passwordHash)

        val entity = entityTemplate
            .selectOne(composeIdQuery(subscriber.uuid), SubscriberEntity::class.java)
            .awaitSingle()

        return entityTemplate.update(entity.update(subscriber, passwordHash)).awaitSingle().getId()
    }

    private fun composeIdQuery(uuid: Subscriber.Uuid) : Query =
        Query.query(Criteria.where(ID_COLUMN).`is`(uuid.toString()))

    private suspend fun insert(subscriber: Subscriber, passwordHash: String? = null) : Subscriber.Uuid =
        SubscriberEntity.new(subscriber, passwordHash).let {
            entityTemplate.insert(it).awaitSingle().getId()
        }
}
