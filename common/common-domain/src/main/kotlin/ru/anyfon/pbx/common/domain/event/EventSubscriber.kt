package ru.anyfon.pbx.common.domain.event

import kotlin.reflect.KClass

interface EventSubscriber<Event : DomainEvent> {
    val eventType: KClass<Event>
    fun handleEvent(event: Event)
}
