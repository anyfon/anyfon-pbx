package ru.anyfon.pbx.manager.domain.subscriber

interface SubscriberRepository {

    suspend fun findByFilters(): List<Subscriber>

    suspend fun findById(uuid: Subscriber.Uuid): Subscriber?

    suspend fun remove(uuid: Subscriber.Uuid)

    suspend fun save(subscriber: Subscriber, passwordHash: String? = null): Subscriber.Uuid

}
