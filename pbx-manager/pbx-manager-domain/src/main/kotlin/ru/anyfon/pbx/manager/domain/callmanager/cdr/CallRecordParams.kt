package ru.anyfon.pbx.manager.domain.callmanager.cdr

import ru.anyfon.pbx.common.domain.FilterParams
import ru.anyfon.pbx.common.domain.type.IP
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.Tenant
import ru.anyfon.pbx.manager.domain.callmanager.CallDirection
import ru.anyfon.pbx.manager.domain.callmanager.DialStatus
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber
import java.time.LocalDate
import java.time.LocalDateTime

object CallRecordParams {
    class Data(
        val timestamp: LocalDateTime,
        val fromNumber: PhoneNumber.Any,
        val toNumber: PhoneNumber.Any,
        val tenantId: Tenant.ID,
        val subscriberUuid: Subscriber.Uuid,
        val sourceIpV4: IP.V4?,
        val sourceIpV6: IP.V6?,
        val apps: Iterable<CallApp>
    )

    class Filter(
        val tenantId: Tenant.ID? = null,
        val subscribersUuid: List<Subscriber.Uuid>,
        val direction: List<CallDirection>,
        val status: List<DialStatus>,
        val from: List<PhoneNumber.Any>,
        val members: List<PhoneNumber.Any>,
        val startDate: LocalDateTime = LocalDate.now().atStartOfDay(),
        val endDate: LocalDateTime = LocalDateTime.now(),
        override val offset: Int = 0,
        override val limit: Int = 100,
        override val sortBy: List<String>
    ) : FilterParams
}
