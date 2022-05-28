package ru.anyfon.web.auth.domain.tenant

import ru.anyfon.pbx.common.domain.type.Tenant
import ru.anyfon.web.auth.domain.user.User

class TenantDetails(
    override val id: Tenant.ID,
    override val uniqueName: Tenant.UniqueName,
    val enabled: Boolean,
    val users: Iterable<User>
) : Tenant
