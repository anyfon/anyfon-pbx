package ru.anyfon.web.auth.domain.tenant

import ru.anyfon.pbx.common.domain.DomainEntity
import ru.anyfon.pbx.common.domain.Value
import ru.anyfon.pbx.common.domain.type.TenantID
import ru.anyfon.web.auth.domain.user.User

class Tenant(
    val id: TenantID,
    val uniqueName: UniqueName,
    val enabled: Boolean,
    val users: Iterable<User>
) : DomainEntity {

    class UniqueName(name: String) : Value.AsString(name, NAME_PATTERN) {
        companion object {
            const val NAME_PATTERN = "[a-z\\_\\-]{5,15}"
        }
    }
}
