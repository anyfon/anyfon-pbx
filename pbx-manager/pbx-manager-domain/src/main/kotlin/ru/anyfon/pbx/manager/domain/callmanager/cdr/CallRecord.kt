package ru.anyfon.pbx.manager.domain.callmanager.cdr

import ru.anyfon.pbx.common.domain.DomainEntity
import ru.anyfon.pbx.common.domain.type.ExecutionStatus
import ru.anyfon.pbx.common.domain.type.IP
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.Tenant
import ru.anyfon.pbx.manager.domain.callmanager.CallDirection
import ru.anyfon.pbx.manager.domain.callmanager.CallID
import ru.anyfon.pbx.manager.domain.callmanager.DialStatus
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber
import java.time.LocalDateTime

class CallRecord(
    val callId: CallID,
    val timestamp: LocalDateTime,
    val fromNumber: PhoneNumber.Any,
    val toNumber: PhoneNumber.Any,
    val tenantId: Tenant.ID,
    val subscriberUuid: Subscriber.Uuid,
    val sourceIpV4: IP.V4?,
    val sourceIpV6: IP.V6?,
    apps: Iterable<CallApp>
) : DomainEntity {

    val direction: CallDirection = CallDirection.of(fromNumber, toNumber)

    val executionSequence: ExecutionSequence = ExecutionSequence(apps)

    val status: DialStatus = composeStatus(executionSequence.allOperations)

    private fun composeStatus(operations: Iterable<CallOperation>) : DialStatus {
        val dialOperations = operations.filter {
            it.commandName == CallOperation.DIAL_COMMAND
        }

        val isAnswered = dialOperations.any { op ->
            op.replies.any {
                it.status == ExecutionStatus.Success
            }
        }

        if (isAnswered) return DialStatus.Answered

        return DialStatus.NoAnswered
    }
}
