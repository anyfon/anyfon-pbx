package ru.anyfon.pbx.manager.domain.callmanager.cdr.descriptor

import ru.anyfon.pbx.common.domain.type.ExecutionStatus
import ru.anyfon.pbx.common.domain.type.IP
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.Tenant
import ru.anyfon.pbx.manager.domain.callmanager.AppName
import ru.anyfon.pbx.manager.domain.callmanager.CommandName
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber
import java.time.LocalDateTime

interface CallRecordDescriptor {

    fun create(
        tenantId: Tenant.ID,
        subscriberUuid: Subscriber.Uuid,
        fromNumber: PhoneNumber.Any,
        toNumber: PhoneNumber.Any,
        sourceIpV4: IP.V4?,
        sourceIpV6: IP.V6?,
        timestamp: LocalDateTime = LocalDateTime.now()
    ) : CallRecordStage


    interface CallRecordStage : CallRecordPersister {

        fun addApp(name: AppName) : CallRecordAppStage
    }

    interface CallRecordAppStage : CallRecordStage {

        fun addOperation(
            commandName: CommandName,
            dest: String,
            timestamp: LocalDateTime = LocalDateTime.now()
        ): CallOperationStage

    }

    interface CallOperationStage : CallRecordAppStage {

        fun addReply(
            status: ExecutionStatus,
            response: String,
            timestamp: LocalDateTime = LocalDateTime.now()
        ): CallRecordOperationReplyStage

        fun addOperationExtra(
            name: String,
            value: Any
        ): CallOperationStage

    }

    interface CallRecordOperationReplyStage : CallOperationStage {

        fun addReplyExtra(
            name: String,
            value: Any
        ): CallRecordOperationReplyStage

    }
}
