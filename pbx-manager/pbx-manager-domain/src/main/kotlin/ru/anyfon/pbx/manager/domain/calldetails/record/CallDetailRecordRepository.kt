package ru.anyfon.pbx.manager.domain.calldetails.record

import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.TenantId
import ru.anyfon.pbx.manager.domain.calldetails.CallDirection
import ru.anyfon.pbx.manager.domain.calldetails.CallId
import ru.anyfon.pbx.manager.domain.calldetails.CallStatus
import ru.anyfon.pbx.manager.domain.siptrunk.SipTrunk
import java.time.LocalDateTime

interface CallDetailRecordRepository {

    suspend fun add(
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
    )

    suspend fun findAll(
        tenantID: TenantId,
        dateFrom: LocalDateTime,
        dateTo: LocalDateTime,
        fromNumbers: List<PhoneNumber.Any>,
        toNumbers: List<PhoneNumber.Any>,
        directions: List<CallDirection>,
        statuses: List<CallStatus>,
        limit: Int,
        offset: Int,
        groupBy: List<GroupBy>
    ): List<CallDetailRecord>

    enum class GroupBy {
        MONTH {
            override val order: Int = 1
        },
        DAY {
            override val order: Int = 2
        },
        USER_GROUP {
            override val order: Int = 3
        },
        USER {
            override val order: Int = 4
        };

        abstract val order: Int
    }
}
