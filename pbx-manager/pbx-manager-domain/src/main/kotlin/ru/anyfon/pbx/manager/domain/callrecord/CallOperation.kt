package ru.anyfon.pbx.manager.domain.callrecord

import ru.anyfon.pbx.common.domain.EntityID
import ru.anyfon.pbx.common.domain.Value
import java.time.LocalDateTime

interface CallOperation {
    val id: ID
    val rootId: CallRecord.ID
    val parentId: ID
    val appName: AppName
    val startDateTime: LocalDateTime

    class ID(id: String) : EntityID.AsString(id)

    class AppName(name: String) : Value.AsString(name, "[\\w]+")

}
