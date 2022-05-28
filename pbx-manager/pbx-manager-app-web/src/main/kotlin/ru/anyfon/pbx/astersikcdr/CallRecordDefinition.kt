package ru.anyfon.pbx.astersikcdr

import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.TenantID
import java.time.LocalDateTime

class CallRecordDefinition(
    val tenantId: TenantID,
    val fromNumber: PhoneNumber.Any,
    val toNumber: PhoneNumber.Any,
    val timestamp: LocalDateTime
)
