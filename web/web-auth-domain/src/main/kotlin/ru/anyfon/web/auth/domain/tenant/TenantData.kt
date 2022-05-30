package ru.anyfon.web.auth.domain.tenant

import ru.anyfon.pbx.common.domain.Entity
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.Tenant
import ru.anyfon.web.auth.domain.user.service.UserData

class TenantData(
    override val id: Tenant.ID,
    override val uniqueName: Tenant.UniqueName,
    override val prefixNumber: PhoneNumber.Internal,
    override val enabled: Boolean,
    val users: Iterable<UserData>
) : Tenant, Entity
