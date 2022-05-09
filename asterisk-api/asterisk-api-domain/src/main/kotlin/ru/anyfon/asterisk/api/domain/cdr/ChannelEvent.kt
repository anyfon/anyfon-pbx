package ru.anyfon.asterisk.api.domain.cdr

import ru.anyfon.pbx.common.domain.Value
import ru.anyfon.pbx.common.domain.ValueObject
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import java.time.LocalDateTime

class ChannelEvent(
    val eventDateTime: LocalDateTime,
    val eventType: Type,
    val callRecordId: CallRecord.ID,
    val detailRecordId: CallDetails.ID,
    val cidNum: PhoneNumber.Any?,
    val cidAni: PhoneNumber.Any?,
    val cidRdnis: PhoneNumber.Any?,
    val cidDnid: PhoneNumber.Any?,
    val exten: PhoneNumber.Any?,
    val context: String,
    val appName: String
) : ValueObject {

    enum class Type : Value.EnumAsString {
        ALL,
        CHAN_START, CHAN_END, LINKEDID_END,
        ANSWER, HANGUP,
        BRIDGE_ENTER, BRIDGE_EXIT,
        APP_START, APP_END,
        PARK_START, PARK_END,
        BLINDTRANSFER, ATTENDEDTRANSFER,
        PICKUP, FORWARD,
        USER_DEFINED, LOCAL_OPTIMIZE

    }
}
