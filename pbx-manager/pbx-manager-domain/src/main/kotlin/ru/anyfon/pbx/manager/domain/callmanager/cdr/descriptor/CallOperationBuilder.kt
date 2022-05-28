package ru.anyfon.pbx.manager.domain.callmanager.cdr.descriptor

import ru.anyfon.pbx.manager.domain.callmanager.CommandName
import ru.anyfon.pbx.manager.domain.callmanager.cdr.CallOperation
import java.time.LocalDateTime

class CallOperationBuilder(
    private val commandName: CommandName,
    private val dest: String,
    private val timestamp: LocalDateTime = LocalDateTime.now()
) {
    private val replies: MutableList<CallOperationReplyBuilder> = mutableListOf()

    private val extra: MutableMap<String, Any> = mutableMapOf()

    fun addExtra(name: String, value: Any) = extra.put(name, value)

    fun addReply(reply: CallOperationReplyBuilder) = replies.add(reply)

    fun build() : CallOperation = CallOperation(
        timestamp,
        commandName,
        dest,
        extra.toMap(),
        replies.map { it.build() }
    )
}
