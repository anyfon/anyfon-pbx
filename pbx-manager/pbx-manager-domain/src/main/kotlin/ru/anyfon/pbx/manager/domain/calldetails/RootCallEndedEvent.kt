package ru.anyfon.pbx.manager.domain.calldetails

import ru.anyfon.pbx.common.domain.event.DomainEvent

class RootCallEndedEvent(
    val callId: CallId
) : DomainEvent()
