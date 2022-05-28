package ru.anyfon.pbx.manager.domain.callmanager.cdr.descriptor

import ru.anyfon.pbx.common.domain.type.IP
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.TenantID
import ru.anyfon.pbx.manager.domain.callmanager.cdr.CallRecordParams
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber
import java.time.LocalDateTime

class CallRecordParamsBuilder(
    private val tenantId: TenantID,
    private val subscriberUuid: Subscriber.Uuid,
    private val fromNumber: PhoneNumber.Any,
    private val toNumber: PhoneNumber.Any,
    private val sourceIpV4: IP.V4?,
    private val sourceIpV6: IP.V6?,
    private val timestamp: LocalDateTime = LocalDateTime.now()
) {
    private val apps: MutableList<CallAppBuilder> = mutableListOf()

    fun addApp(app: CallAppBuilder) = apps.add(app)

    fun build(): CallRecordParams.Data = let {

        val apps = this.apps.map { it.build() }

        CallRecordParams.Data(
            timestamp,
            fromNumber,
            toNumber,
            tenantId,
            subscriberUuid,
            sourceIpV4,
            sourceIpV6,
            apps
        )
    }

}
