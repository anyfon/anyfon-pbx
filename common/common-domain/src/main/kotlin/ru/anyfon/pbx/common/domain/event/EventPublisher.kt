package ru.anyfon.pbx.common.domain.event

interface EventPublisher {

    fun publish(event: DomainEvent)

    fun <T : DomainEvent> subscribe(subscriber: EventSubscriber<T>)

    fun <T : DomainEvent> unsubscribe(subscriber: EventSubscriber<T>)
}
