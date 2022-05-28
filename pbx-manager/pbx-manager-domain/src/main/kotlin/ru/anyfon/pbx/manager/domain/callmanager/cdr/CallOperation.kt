package ru.anyfon.pbx.manager.domain.callmanager.cdr

import ru.anyfon.pbx.common.domain.ValueObject
import ru.anyfon.pbx.common.domain.type.ExecutionStatus
import ru.anyfon.pbx.manager.domain.callmanager.CommandName
import java.time.LocalDateTime

class CallOperation(
    val timestamp: LocalDateTime,
    val commandName: CommandName,
    val dest: String,
    val extra: Map<String, Any>,
    val replies: Iterable<Reply>
) : ValueObject {

    companion object {
        val DIAL_COMMAND = CommandName("DIAL")
    }

    class Reply(
        val timestamp: LocalDateTime,
        val status: ExecutionStatus,
        val response: String,
        val extra: Map<String, Any>
    ) : ValueObject
}
