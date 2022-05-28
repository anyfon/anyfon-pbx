package ru.anyfon.pbx.common.domain.event

abstract class AbstractEventPublisher : EventPublisher {

    private val subscribers: MutableList<EventSubscriber<*>> = mutableListOf()

    override fun publish(event: DomainEvent) {
        TODO("Not yet implemented")
    }

    override fun <T : DomainEvent> subscribe(subscriber: EventSubscriber<T>) =
        checkBeforeSubscribe(subscriber).also {
            subscribers.add(subscriber)
        }

    private fun checkBeforeSubscribe(subscriber: EventSubscriber<*>) {
        if (subscribers.contains(subscriber))
            throw IllegalStateException("Subscriber [ class: ${subscriber::class.qualifiedName} ] already subscribed")
    }

    override fun <T : DomainEvent> unsubscribe(subscriber: EventSubscriber<T>) =
        checkBeforeUnsubscribe(subscriber).also {
            subscribers.remove(subscriber)
        }

    private fun checkBeforeUnsubscribe(subscriber: EventSubscriber<*>) {
        if (!subscribers.contains(subscriber))
            throw IllegalStateException("Subscriber [ class: ${subscriber::class.qualifiedName} ] not found")
    }
}
