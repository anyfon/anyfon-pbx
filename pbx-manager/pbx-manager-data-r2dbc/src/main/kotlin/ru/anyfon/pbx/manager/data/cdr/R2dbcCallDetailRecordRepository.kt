package ru.anyfon.pbx.manager.data.cdr

import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.TenantID
import ru.anyfon.pbx.manager.domain.callrecord.CallDirection
import ru.anyfon.pbx.manager.domain.callrecord.CallId
import ru.anyfon.pbx.manager.domain.callrecord.CallStatus
import ru.anyfon.pbx.manager.domain.callrecord.record.CallDetailRecord
import ru.anyfon.pbx.manager.domain.callrecord.record.CallDetailRecordRepository
import ru.anyfon.pbx.manager.domain.siptrunk.SipTrunk
import java.time.LocalDateTime

class R2dbcCallDetailRecordRepository : CallDetailRecordRepository {
    override suspend fun add(
        id: CallId,
        rootId: CallId,
        callDateTime: LocalDateTime,
        fromNumber: PhoneNumber.Any,
        toNumber: PhoneNumber.Any,
        answerTime: Int?,
        usingDuration: Int?,
        callDuration: Int,
        status: CallStatus,
        hasRecord: Boolean,
        tenantID: TenantID,
        sipTrunkId: SipTrunk.ID,
        direction: CallDirection
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(
        tenantID: TenantID,
        dateFrom: LocalDateTime,
        dateTo: LocalDateTime,
        fromNumbers: List<PhoneNumber.Any>,
        toNumbers: List<PhoneNumber.Any>,
        directions: List<CallDirection>,
        statuses: List<CallStatus>,
        limit: Int,
        offset: Int,
        groupBy: List<CallDetailRecordRepository.GroupBy>
    ): List<CallDetailRecord> {
        TODO("Not yet implemented")
    }
}
