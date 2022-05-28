package ru.anyfon.pbx.manager.domain.subscriber.user

import ru.anyfon.pbx.common.domain.DomainEntity
import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.FullName
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.Tenant
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber

class SipUser(
    val subscriberUuid: Subscriber.Uuid,
    val subscriberUsername: Subscriber.Username,
    val extension: PhoneNumber.Extension,
    val name: FullName,
    val email: Email?,
    val tenantName: Tenant.UniqueName,
    val enabled: Boolean,
    val registered: Boolean
) : DomainEntity
