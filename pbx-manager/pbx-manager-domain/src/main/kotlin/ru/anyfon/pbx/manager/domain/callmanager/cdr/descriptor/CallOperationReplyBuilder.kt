package ru.anyfon.pbx.manager.domain.callmanager.cdr.descriptor

import ru.anyfon.pbx.common.domain.type.ExecutionStatus
import ru.anyfon.pbx.manager.domain.callmanager.cdr.CallOperation
import java.time.LocalDateTime

class CallOperationReplyBuilder(
    private val status: ExecutionStatus,
    private val response: String,
    private val timestamp: LocalDateTime
) {
    private val extra: MutableMap<String, Any> = mutableMapOf()

    fun addExtra(name: String, value: Any) = extra.put(name, value)

    fun build(): CallOperation.Reply = CallOperation.Reply(
        timestamp,
        status,
        response,
        extra.toMap()
    )
}
