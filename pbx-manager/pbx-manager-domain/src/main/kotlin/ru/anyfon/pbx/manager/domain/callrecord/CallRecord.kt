package ru.anyfon.pbx.manager.domain.callrecord

import ru.anyfon.pbx.common.domain.EntityID
import ru.anyfon.pbx.common.domain.ValueObject
import ru.anyfon.pbx.common.domain.type.TenantID

class CallRecord(
    val id: ID,
    val rootCall: CallDetails,
    val callSequence: CallSequence,
    val tenantID: TenantID,
    val allCalls: Iterable<CallDetails>,

    ) : ValueObject {

    class ID(id: String) : EntityID.AsString(id)

    enum class GroupBy {
        ALL,
    }
}
