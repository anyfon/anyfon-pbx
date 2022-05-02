package ru.anyfon.pbx.manager.data.cdr

import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.TenantId
import ru.anyfon.pbx.manager.domain.calldetails.CallDirection
import ru.anyfon.pbx.manager.domain.calldetails.CallId
import ru.anyfon.pbx.manager.domain.calldetails.CallStatus
import ru.anyfon.pbx.manager.domain.calldetails.record.CallDetailRecord
import ru.anyfon.pbx.manager.domain.calldetails.record.CallDetailRecordRepository
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
        tenantID: TenantId,
        sipTrunkId: SipTrunk.ID,
        direction: CallDirection
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(
        tenantID: TenantId,
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
