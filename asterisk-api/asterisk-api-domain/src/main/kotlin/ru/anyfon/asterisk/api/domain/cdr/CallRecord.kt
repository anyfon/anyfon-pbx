package ru.anyfon.asterisk.api.domain.cdr

import ru.anyfon.pbx.common.domain.DomainEntity
import ru.anyfon.pbx.common.domain.EntityID


class CallRecord(
    val id: ID,
    val details: List<CallDetails>,
    val events: List<ChannelEvent>,
    val sequence: Int
) : DomainEntity {
    class ID(id: String) : EntityID.AsString(id, CallDetails.ID.ID_PATTERN)
}
