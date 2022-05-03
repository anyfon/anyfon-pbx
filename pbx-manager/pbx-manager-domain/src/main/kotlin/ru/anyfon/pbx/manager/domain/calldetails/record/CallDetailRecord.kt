package ru.anyfon.pbx.manager.domain.calldetails.record

import ru.anyfon.pbx.common.domain.ValueObject
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.TenantID
import ru.anyfon.pbx.manager.domain.calldetails.CallDirection
import ru.anyfon.pbx.manager.domain.calldetails.CallId
import ru.anyfon.pbx.manager.domain.calldetails.CallStatus
import ru.anyfon.pbx.manager.domain.siptrunk.SipTrunk
import java.time.LocalDateTime

class CallDetailRecord(
    val id: CallId,
    val rootId: CallId,
    val direction: CallDirection,
    val callDateTime: LocalDateTime,
    val fromNumber: PhoneNumber.Any,
    val toNumber: PhoneNumber.Any,
    val answerTime: Int,
    val useDuration: Int,
    val callDuration: Int,
    val status: CallStatus,
    val hasRecord: Boolean,
    val tenantId: TenantID,
    val trunkId: SipTrunk.ID?
) : ValueObject {

    val isRoot = id == rootId
}
