package ru.anyfon.asterisk.api.domain.cdr

import ru.anyfon.pbx.common.domain.DomainEntity
import ru.anyfon.pbx.common.domain.EntityID
import ru.anyfon.pbx.common.domain.Value


class CallRecord(
    val id: ID,
    val details: List<CallDetails>,
    val events: List<ChannelEvent>
) : DomainEntity {

    val lastEventId: Value.PositiveZeroInt = events.first {
        it.eventType == ChannelEvent.Type.LINKEDID_END
    }.let {
        it.id
    }
    class ID(id: String) : EntityID.AsString(id, CallDetails.ID.ID_PATTERN) {
        companion object {
            val EMPTY = ID(CallDetails.ID.EMPTY.toString())
        }
        override fun isEmpty(): Boolean = this == EMPTY

    }
}
