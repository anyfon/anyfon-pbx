package ru.anyfon.pbx.manager.domain.subscriber.user.service

import ru.anyfon.pbx.common.domain.DomainEntity
import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.FullName
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.Tenant
import ru.anyfon.pbx.manager.domain.subscriber.Subscriber
import ru.anyfon.pbx.manager.domain.subscriber.user.SipUser

class SipUserDetails(
    val subscriberUuid: Subscriber.Uuid,
    val subscriberUsername: Subscriber.Username,
    val extension: PhoneNumber.Extension,
    val name: FullName,
    val email: Email?,
    val tenantName: Tenant.UniqueName,
    val description: String,
    val enabled: Boolean,
    val registered: Boolean
) : DomainEntity {

    constructor(
        sipUser: SipUser,
        subscriber: Subscriber,
        tenantPrefix: PhoneNumber.Internal,
        registered: Boolean
    ) : this(
        subscriber.uuid,
        subscriber.username,
        PhoneNumber.Extension(tenantPrefix, sipUser.internalNumber),
        sipUser.name,
        sipUser.email,
        sipUser.tenantName,
        sipUser.description,
        subscriber.enabled,
        registered
    )
}
