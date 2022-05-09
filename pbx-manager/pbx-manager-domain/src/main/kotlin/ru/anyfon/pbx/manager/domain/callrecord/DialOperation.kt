package ru.anyfon.pbx.manager.domain.callrecord

import java.time.LocalDateTime

class DialOperation(
    override val id: CallOperation.ID,
    override val rootId: CallRecord.ID,
    override val parentId: CallOperation.ID,
    val callMetadata: CallDetails
    ) : CallOperation {

    companion object {
        const val APP_NAME = "Dial"
    }

    override val startDateTime: LocalDateTime = callMetadata.startDateTime

    override val appName: CallOperation.AppName = CallOperation.AppName(APP_NAME)
}
