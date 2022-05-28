package ru.anyfon.pbx.astersikcdr

import ru.anyfon.asterisk.api.domain.cdr.CallRecord
import ru.anyfon.pbx.manager.domain.callmanager.cdr.descriptor.CallRecordDescriptor

class AsteriskCdrHandlerImpl(
    private val callRecordDescriptor: CallRecordDescriptor
) : AsteriskCdrHandler {
    override fun handle(callRecord: CallRecord) {
        val rootCall = callRecord.details.firstOrNull {
            it.id.toString() == it.callRecordId.toString()
        }
    }
}
