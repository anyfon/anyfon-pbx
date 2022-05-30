package ru.anyfon.web.auth.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import ru.anyfon.pbx.common.domain.type.TenantID
import ru.anyfon.web.auth.domain.tenant.TenantData
import ru.anyfon.web.auth.domain.user.service.UserData

@Table("tenant")
class TenantEntity(
    @Id
    private val id: String = "",
    private val uniqueName: String = "",
    private val enabled: Boolean = false
) {

    fun toTenant(users: Iterable<UserData>): TenantData =
        TenantData(
            TenantID(id),
            TenantData.UniqueName(uniqueName),
            enabled,
            users
        )
}
