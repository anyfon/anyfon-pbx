package ru.anyfon.pbx.manager.domain.subscriber.service

import ru.anyfon.pbx.manager.domain.subscriber.Subscriber

interface SubscriberService {

    suspend fun findByFilters(): List<SubscriberDetails>

    suspend fun findById(uuid: Subscriber.Uuid): SubscriberDetails

    suspend fun create(subscriber: Subscriber, password: Subscriber.Password?): Subscriber.Uuid

    suspend fun delete(uuid: Subscriber.Uuid)

}
