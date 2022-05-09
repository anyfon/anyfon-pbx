package ru.anyfon.pbx.manager.domain.callrecord

import ru.anyfon.pbx.common.domain.ValueObject
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.TimeDuration
import ru.anyfon.pbx.manager.domain.siptrunk.SipTrunk
import java.time.LocalDateTime

class CallDetails(
    val id: CallOperation.ID,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val direction: CallDirection,
    val fromNumber: PhoneNumber.Any,
    val toNumber: PhoneNumber.Any,
    val answerTime: TimeDuration,
    val usingDuration: TimeDuration,
    val callDuration: TimeDuration,
    val terminationStatus: TerminationStatus,
    val viaTrunkId: SipTrunk.ID
) : ValueObject
