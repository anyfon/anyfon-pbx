package ru.anyfon.pbx.manager.data.callmanager.subscriber

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber

@Table("sip_subscriber")
class SubscriberEntity private constructor(
    @Id
    val id: String? = null,
    private val username: String,
    private val password: String? = null,
    private val enabled: Boolean = false
) {
    constructor(
        username: String,
        enabled: Boolean = false,
        passwordHash: String? = null
    ) : this(null, username, passwordHash, enabled)

    fun toSubscriber(): Subscriber = Subscriber(
        Subscriber.Uuid(id),
        Subscriber.Username(username),
        enabled
    )

    fun update(
        username: String?,
        enabled: Boolean?,
        passwordHash: String? = null
    ): SubscriberEntity = SubscriberEntity(
        id,
        username ?: this.username,
        passwordHash ?: this.password,
        enabled ?: this.enabled
    )
}
