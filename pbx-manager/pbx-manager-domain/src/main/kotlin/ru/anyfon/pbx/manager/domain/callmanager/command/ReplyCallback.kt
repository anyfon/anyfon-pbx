package ru.anyfon.pbx.manager.domain.callmanager.command

import ru.anyfon.pbx.common.domain.type.ExecutionStatus

interface ReplyCallback {

    fun onReply(status: ExecutionStatus, response: Any)
}
