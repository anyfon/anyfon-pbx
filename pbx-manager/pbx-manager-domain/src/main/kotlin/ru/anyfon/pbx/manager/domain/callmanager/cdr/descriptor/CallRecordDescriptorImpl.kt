package ru.anyfon.pbx.manager.domain.callmanager.cdr.descriptor

import ru.anyfon.pbx.common.domain.type.ExecutionStatus
import ru.anyfon.pbx.common.domain.type.IP
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.Tenant
import ru.anyfon.pbx.manager.domain.callmanager.AppName
import ru.anyfon.pbx.manager.domain.callmanager.CommandName
import ru.anyfon.pbx.manager.domain.callmanager.cdr.CallRecordRepository
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber
import java.time.LocalDateTime

class CallRecordDescriptorImpl(
    private val callRecordRepository: CallRecordRepository
) : CallRecordDescriptor {

    override fun create(
        tenantId: Tenant.ID,
        subscriberUuid: Subscriber.Uuid,
        fromNumber: PhoneNumber.Any,
        toNumber: PhoneNumber.Any,
        sourceIpV4: IP.V4?,
        sourceIpV6: IP.V6?,
        timestamp: LocalDateTime
    ): CallRecordStage = CallRecordParamsBuilder(
        tenantId,
        subscriberUuid,
        fromNumber,
        toNumber,
        sourceIpV4,
        sourceIpV6,
        timestamp
    ).let {
        CallRecordStage(
            CallRecordPersister.Base(it, callRecordRepository),
            it
        )
    }

    class CallRecordStage(
        private val recordPersister: CallRecordPersister,
        private val recordParamsBuilder: CallRecordParamsBuilder
    ) : CallRecordDescriptor.CallRecordStage {

        override fun addApp(name: AppName): CallRecordDescriptor.CallRecordAppStage =
            CallAppBuilder(name).also {
                recordParamsBuilder.addApp(it)
            }.let {
                CallRecordAppStage(recordPersister, it, this)
            }

        override suspend fun save() = recordPersister.save()
    }


    class CallRecordAppStage(
        private val recordPersister: CallRecordPersister,
        private val appBuilder: CallAppBuilder,
        private val callRecordStage: CallRecordDescriptor.CallRecordStage
    ) : CallRecordDescriptor.CallRecordAppStage {

        override fun addApp(name: AppName): CallRecordDescriptor.CallRecordAppStage = callRecordStage.addApp(name)

        override fun addOperation(
            commandName: CommandName,
            dest: String,
            timestamp: LocalDateTime
        ): CallRecordDescriptor.CallOperationStage =
            CallOperationBuilder(commandName, dest, timestamp).also {
                appBuilder.addOperation(it)
            }.let {
                CallOperationStage(recordPersister, this, it)
            }

        override suspend fun save() = recordPersister.save()
    }

    class CallOperationStage(
        private val recordPersister: CallRecordPersister,
        private val callRecordAppStage: CallRecordDescriptor.CallRecordAppStage,
        private val callOperationBuilder: CallOperationBuilder
    ) : CallRecordDescriptor.CallOperationStage {

        override fun addReply(
            status: ExecutionStatus,
            response: String,
            timestamp: LocalDateTime
        ): CallRecordDescriptor.CallRecordOperationReplyStage =
            CallOperationReplyBuilder(status, response, timestamp).also {
                callOperationBuilder.addReply(it)
            }.let {
                CallOperationReplyStage(recordPersister, it, this)
            }


        override fun addOperationExtra(
            name: String,
            value: Any
        ): CallRecordDescriptor.CallOperationStage = callOperationBuilder.addExtra(name, value).let { this }

        override fun addOperation(
            commandName: CommandName,
            dest: String,
            timestamp: LocalDateTime
        ): CallRecordDescriptor.CallOperationStage = callRecordAppStage.addOperation(commandName, dest, timestamp)

        override fun addApp(name: AppName): CallRecordDescriptor.CallRecordAppStage = callRecordAppStage.addApp(name)

        override suspend fun save() = recordPersister.save()

    }

    class CallOperationReplyStage(
        private val recordPersister: CallRecordPersister,
        private val callOperationReplyBuilder: CallOperationReplyBuilder,
        private val callOperationStage: CallRecordDescriptor.CallOperationStage
    ) : CallRecordDescriptor.CallRecordOperationReplyStage {

        override fun addReplyExtra(
            name: String,
            value: Any
        ): CallRecordDescriptor.CallRecordOperationReplyStage =
            callOperationReplyBuilder.addExtra(name, value).let { this }

        override fun addReply(
            status: ExecutionStatus,
            response: String,
            timestamp: LocalDateTime
        ): CallRecordDescriptor.CallRecordOperationReplyStage = callOperationStage.addReply(status, response, timestamp)

        override fun addOperationExtra(
            name: String,
            value: Any
        ): CallRecordDescriptor.CallOperationStage = callOperationStage.addOperationExtra(name, value)

        override fun addOperation(
            commandName: CommandName,
            dest: String,
            timestamp: LocalDateTime
        ): CallRecordDescriptor.CallOperationStage = callOperationStage.addOperation(commandName, dest, timestamp)

        override fun addApp(
            name: AppName
        ): CallRecordDescriptor.CallRecordAppStage = callOperationStage.addApp(name)

        override suspend fun save() = recordPersister.save()

    }

}
