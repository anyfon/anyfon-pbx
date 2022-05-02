package ru.anyfon.pbx.common.domain.event

import java.time.LocalDateTime

abstract class DomainEvent {
    val occurredOn: LocalDateTime = LocalDateTime.now()
}
