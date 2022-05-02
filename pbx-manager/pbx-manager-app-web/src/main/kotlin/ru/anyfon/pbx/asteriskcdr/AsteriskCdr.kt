package ru.anyfon.pbx.asteriskcdr

import java.time.LocalDateTime

class AsteriskCdr(
    val uniqueId: String = "",
    val linkedId: String = "",
    val callDateTime: LocalDateTime = LocalDateTime.MAX,
    val src: String? = null,
    val dst: String? = null,
    val did: String? = null,
    val cnum: String? = null,
    val recordingFile: String? = null,
    val dcontext: String? = null,
    val status: Status? = null,
    val direction: Direction? = null
) {
    enum class Status {
        ANSWERED, NO_ANSWER, FAILED, BUSY;
    }

    enum class Direction {
        INBOUND, OUTBOUND, INTERNAL
    }
}
