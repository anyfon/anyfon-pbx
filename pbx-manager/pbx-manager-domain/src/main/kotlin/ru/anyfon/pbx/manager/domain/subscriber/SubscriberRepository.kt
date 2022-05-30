package ru.anyfon.pbx.manager.domain.subscriber

import ru.anyfon.pbx.common.domain.service.request.Request

interface SubscriberRepository {

    suspend fun findAllById(uuids: Request.Valid<Iterable<Subscriber.Uuid>>): List<Subscriber>

    suspend fun findById(uuid: Request.Valid<Subscriber.Uuid>): Subscriber?

    suspend fun deleteAllById(uuids: Request.Valid<Iterable<Subscriber.Uuid>>)

    suspend fun add(
        username: Request.Valid<Subscriber.Username>,
        enabled: Boolean = false,
        passwordHash: String? = null
    ): Subscriber.Uuid

    suspend fun update(
        subscriberUuid: Request.Valid<Subscriber.Uuid>,
        username: Request.Valid<Subscriber.Username>? = null,
        enabled: Boolean? = null,
        passwordHash: String? = null
    )

}
