package ru.anyfon.pbx.manager.data.callmanager.cdr

import com.fasterxml.jackson.databind.ObjectMapper
import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.pbx.common.domain.type.ExecutionStatus
import ru.anyfon.pbx.common.domain.type.IP
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.Tenant
import ru.anyfon.pbx.manager.domain.callmanager.AppName
import ru.anyfon.pbx.manager.domain.callmanager.CallID
import ru.anyfon.pbx.manager.domain.callmanager.CommandName
import ru.anyfon.pbx.manager.domain.callmanager.cdr.CallApp
import ru.anyfon.pbx.manager.domain.callmanager.cdr.CallOperation
import ru.anyfon.pbx.manager.domain.callmanager.cdr.CallRecord
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber

class CallRecordsMapper(
    operations: Iterable<CallOperationEntity>,
    replies: Iterable<CommandReplyEntity>,
    private val objectMapper: ObjectMapper
) {
    private val operationsMap = operations.groupBy { it.callId }
    private val repliesMap = replies.groupBy { it.operationId }


    fun map(records: Iterable<CallRecordEntity>): List<CallRecord> {

        val recordMap = records.associateBy { it.callId ?: 0L }

        return recordMap.values.map { record ->
            CallRecord(
                CallID(record.callId),
                record.timestamp ?: throw NullPointerException(),
                PhoneNumber.Any(record.fromNumber),
                PhoneNumber.Any(record.toNumber),
                Tenant.ID(record.tenantId),
                Subscriber.Uuid(record.subscriberId),
                record.sourceIpV4?.let { ConvertUtils.tryOrNull { IP.V4(it) } },
                record.sourceIpV4?.let { ConvertUtils.tryOrNull { IP.V6(it) } },
                createApps(operationsMap[record.callId] ?: emptyList())
            )
        }
    }

    fun map(record: CallRecordEntity) : CallRecord = map(listOf(record)).first()

    @Suppress("UNCHECKED_CAST")
    private fun createApps(operations: List<CallOperationEntity>): List<CallApp> =
        operations.filter { it.root }.map { root ->
            val parentOperations = mutableListOf<CallOperationEntity>()
            val parentOperationsMap = operations.groupBy { it.parentId }
            parentOperations.add(root)
            mergeParentOperations(root.id ?: 0L, parentOperations, parentOperationsMap)
            CallApp(
                AppName(root.appName),
                operations.map { operation ->
                    CallOperation(
                        operation.timestamp,
                        CommandName(operation.commandName),
                        operation.dest,
                        ConvertUtils.tryOrNull {
                            objectMapper.readValue(operation.extra, Map::class.java) as Map<String, Any>
                        } ?: emptyMap(),
                        createOperationReplies(operation.id)
                    )
                }
            )
        }

    private fun mergeParentOperations(
        operationId: Long,
        operations: MutableList<CallOperationEntity>,
        parentOperationsMap: Map<Long, List<CallOperationEntity>>
    ) {
        val parentOperations = parentOperationsMap[operationId] ?: emptyList()
        operations.addAll(parentOperations)
        parentOperations.forEach {
            mergeParentOperations(it.id ?: 0L, operations, parentOperationsMap)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun createOperationReplies(operationId: Long?): List<CallOperation.Reply> =
        repliesMap[operationId]?.map {
            CallOperation.Reply(
                it.timestamp,
                ExecutionStatus.Custom(it.status),
                it.response,
                ConvertUtils.tryOrNull {
                    objectMapper.readValue(it.extra, Map::class.java) as Map<String, Any>
                } ?: emptyMap()
            )
        } ?: emptyList()
}
