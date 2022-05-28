package ru.anyfon.pbx.manager.data.callmanager.cdr

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("call_operation")
data class CallOperationEntity(
    @Id
    val id: Long? = null,
    val callId: Long? = null,
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val root: Boolean = false,
    val appName: String? = null,
    val commandName: String? = null,
    val dest: String = "",
    val parentId: Long = 0,
    val extra: String = ""
)
