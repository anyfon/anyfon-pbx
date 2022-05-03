package ru.anyfon.pbx.manager.domain.subscriber

import ru.anyfon.pbx.common.domain.DomainEntity
import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.FullName
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.TenantID

class SipUser(
    val name: FullName,
    val number: PhoneNumber.Internal,
    val tenantID: TenantID,
    val email: Email?
) : DomainEntity {

    val id = "$tenantID-$number"
}
