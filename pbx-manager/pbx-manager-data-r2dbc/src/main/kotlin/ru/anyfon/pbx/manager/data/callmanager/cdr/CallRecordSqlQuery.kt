package ru.anyfon.pbx.manager.data.callmanager.cdr

import ru.anyfon.pbx.manager.domain.callmanager.cdr.CallOperation
import ru.anyfon.pbx.manager.domain.callmanager.cdr.CallRecordParams

class CallRecordSqlQuery(
    params: CallRecordParams.Filter
) {

    private val whereTimestamp = "c.timestamp > ${params.startDate} AND c.timestamp < ${params.endDate} "

    private val whereFrom = params.from.let {
        if (it.isEmpty()) return@let ""
        return@let "AND c.from_number IN (${it.joinToString()}) "
    }

    private val whereMember = params.members.let {
        if (it.isEmpty()) return@let ""
        return@let "AND o.dest IN (${it.joinToString()}) AND o.command_name = '${CallOperation.DIAL_COMMAND}' "
    }

    private val whereTenantId = params.tenantId?.let {
        return@let "AND c.tenant_id = $it "
    } ?: ""

    private val whereSubscribersId = params.subscribersUuid.let {
        if (it.isEmpty()) return@let ""
        return@let "AND c.subscriber_id IN (${it.joinToString()}) "
    }

    private val whereStatus = params.status.let {
        if (it.isEmpty()) return@let ""
        return@let "AND r.response IN (${it.joinToString()}) AND c.command_name '${CallOperation.DIAL_COMMAND}' "
    }

    private val whereDirection = params.direction.let {
        if (it.isEmpty()) return@let ""
        return@let "AND c.direction IN (${it.joinToString()}) "
    }

    private val suffixQuery = let {
        "LIMIT ${params.offset}, ${params.limit} ORDER BY ${params.sortBy.joinToString()}"
    }

    fun getQuery(): String {
        return "SELECT c.* FROM call_record c " +
                "LEFT JOIN call_operation o ON o.call_id = c.call_id " +
                "LEFT JOIN call_command_reply r ON o.id = r.operation_id " +
                whereTimestamp +
                whereFrom +
                whereMember +
                whereTenantId +
                whereSubscribersId +
                whereStatus +
                whereDirection +
                suffixQuery
    }

}
