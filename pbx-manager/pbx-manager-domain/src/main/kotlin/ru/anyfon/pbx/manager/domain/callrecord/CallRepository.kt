package ru.anyfon.pbx.manager.domain.callrecord

import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.TenantID
import java.time.LocalDateTime

interface CallRepository {

    suspend fun findCalls(
        start: LocalDateTime,
        end: LocalDateTime,
        tenantID: TenantID,
        direction: CallDirection,
        status: TerminationStatus,

    ): List<CallDetails>

    suspend fun findCallRecords(
        start: LocalDateTime,
        end: LocalDateTime,
        tenantID: TenantID,
        status: TerminationStatus,
        direction: CallDirection,
        fromNumbers: Iterable<PhoneNumber.Any>,
        members: Iterable<PhoneNumber.Any>,
        groupBy: GroupingCall
    ): List<CallRecord>

    suspend fun addCallRecord(callRecord: CallRecord)

}
