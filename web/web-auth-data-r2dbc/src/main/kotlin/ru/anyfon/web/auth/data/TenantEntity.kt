package ru.anyfon.web.auth.data

import jdk.jfr.Enabled
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import ru.anyfon.pbx.common.domain.type.TenantID
import ru.anyfon.web.auth.domain.tenant.Tenant
import ru.anyfon.web.auth.domain.user.User

@Table("tenant")
class TenantEntity(
    @Id
    private val id: String = "",
    private val uniqueName: String = "",
    private val enabled: Boolean = false
) {

    fun toTenant(users: Iterable<User>): Tenant =
        Tenant(
            TenantID(id),
            Tenant.UniqueName(uniqueName),
            enabled,
            users
        )
}
