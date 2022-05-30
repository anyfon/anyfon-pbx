package ru.anyfon.pbx.manager.domain.subscriber.user

import ru.anyfon.pbx.common.domain.Entity
import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.FullName
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.Tenant
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber

class SipUser(
    val subscriberUuid: Subscriber.Uuid,
    val internalNumber: PhoneNumber.Internal,
    val name: FullName,
    val email: Email?,
    val tenantName: Tenant.UniqueName,
    val description: String
) : Entity
