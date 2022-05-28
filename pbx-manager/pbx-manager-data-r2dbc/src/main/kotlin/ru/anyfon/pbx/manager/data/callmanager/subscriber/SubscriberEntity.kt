package ru.anyfon.pbx.manager.data.callmanager.subscriber

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber

@Table("sip_subscriber")
data class SubscriberEntity private constructor(
    @Id
    private val id: String? = null,
    private val username: String? = null,
    private val password: String? = null,
    private val enabled: Boolean = false
) {

    companion object {
        fun new(subscriber: Subscriber, passwordHash: String?) : SubscriberEntity {
            if (!subscriber.uuid.isEmpty()) throw IllegalArgumentException(
                "For a new entity subscriber's [ ID ] field must be EMPTY"
            )
            return SubscriberEntity(
                null, subscriber.username.toString(),
                passwordHash,
                subscriber.enabled
            )
        }
    }

    fun toSubscriber(): Subscriber = Subscriber(
        Subscriber.Uuid(id),
        Subscriber.Username(username),
        enabled
    )

    fun update(subscriber: Subscriber, passwordHash: String? = null): SubscriberEntity =
        SubscriberEntity(
            id,
            subscriber.username.toString(),
            passwordHash ?: password,
            subscriber.enabled
        )

    fun getId() : Subscriber.Uuid = Subscriber.Uuid(id)
}
