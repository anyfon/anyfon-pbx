package ru.anyfon.pbx.astersikcdr

import ru.anyfon.asterisk.api.domain.cdr.CallRecord

interface AsteriskCdrHandler {
    fun handle(callRecord: CallRecord)
}
