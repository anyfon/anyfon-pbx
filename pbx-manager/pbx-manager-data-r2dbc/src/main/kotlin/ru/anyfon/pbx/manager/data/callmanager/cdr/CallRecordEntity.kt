package ru.anyfon.pbx.manager.data.callmanager.cdr

import io.r2dbc.spi.Row
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import ru.anyfon.common.util.ConvertUtils
import java.time.LocalDateTime

@Table("call_record")
data class CallRecordEntity(
    @Id
    val callId: Long? = null,
    val timestamp: LocalDateTime? = null,
    val direction: String? = null,
    val fromNumber: String? = null,
    val toNumber: String? = null,
    val tenantId: String? = null,
    val subscriberId: String? = null,
    val sourceIpV4: String? = null,
    val sourceIpV6: String? = null
) {
    companion object {
        fun ofRow(row: Row) : CallRecordEntity = CallRecordEntity(
            ConvertUtils.toNumber(row["call_id"])?.toLong(),
            LocalDateTime.parse(row["timestamp"]?.toString()),
            row["direction"]?.toString(),
            row["from_number"]?.toString(),
            row["to_number"]?.toString(),
            row["tenant_id"]?.toString(),
            row["subscriber_id"]?.toString(),
            row["source_ip_v4"]?.toString(),
            row["source_ip_v6"]?.toString()
        )
    }
}
