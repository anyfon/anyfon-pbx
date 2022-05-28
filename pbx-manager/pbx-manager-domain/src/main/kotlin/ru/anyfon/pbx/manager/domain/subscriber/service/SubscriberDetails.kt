package ru.anyfon.pbx.manager.domain.subscriber.service

import ru.anyfon.pbx.common.domain.DomainEntity
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber

class SubscriberDetails(
    val uuid: Subscriber.Uuid,
    val username: Subscriber.Username,
    val enabled: Boolean,
    val registered: Boolean
) : DomainEntity {
    constructor(subscriber: Subscriber, registered: Boolean) : this(
        subscriber.uuid,
        subscriber.username,
        subscriber.enabled,
        registered
    )
}
