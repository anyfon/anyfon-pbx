package ru.anyfon.pbx.manager.data.callmanager.cdr

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime


@Table("call_command_reply")
data class CommandReplyEntity(
    @Id
    val id: Long? = null,
    val operationId: Long = 0L,
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: String = "",
    val response: String = "",
    val extra: String = ""
)
