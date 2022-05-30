package ru.anyfon.asterisk.api.domain.cdr

import ru.anyfon.pbx.common.domain.EntityID
import ru.anyfon.pbx.common.domain.Value
import ru.anyfon.pbx.common.domain.ValueObject
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import java.time.LocalDateTime

class CallDetails(
    val id: ID,
    val callRecordId: CallRecord.ID,
    val callDateTime: LocalDateTime,
    val src: PhoneNumber.Any? = null,
    val dst: PhoneNumber.Any? = null,
    val did: PhoneNumber.Any? = null,
    val cnum: PhoneNumber.Any? = null,
    val hasRecord: Boolean,
    val recordingFileName: String? = null,
    val dcontext: String,
    val status: Status,
    val sequence: Int
) : ValueObject {
    class ID(id: String) : EntityID.AsString(id, ID_PATTERN) {
        companion object {
            const val ID_PATTERN = "\\d{8,10}\\.\\d{1,20}"
            val EMPTY = ID("00000000.0")
        }

        override fun isEmpty(): Boolean = this == EMPTY
    }

    enum class Status : Value.EnumAsString {
        ANSWERED, NO_ANSWER, FAILED, BUSY;
    }
}
