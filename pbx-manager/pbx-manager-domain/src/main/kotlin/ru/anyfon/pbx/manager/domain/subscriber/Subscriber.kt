package ru.anyfon.pbx.manager.domain.subscriber

import ru.anyfon.common.util.StringUtils
import ru.anyfon.pbx.common.domain.Entity
import ru.anyfon.pbx.common.domain.EntityID
import ru.anyfon.pbx.common.domain.Value
import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.Tenant

class Subscriber(
    val uuid: Uuid,
    val username: Username,
    val enabled: Boolean
) : Entity {

    class Uuid(uuid: String?) : EntityID.AsUuidString(uuid) {

        companion object {
            val EMPTY = Uuid(EMPTY_UUID)
        }

        override fun isEmpty(): Boolean = this == EMPTY
    }

    class Username(name: String?) : Value.AsString(name, "[a-z0-9\\-]{10,50}") {
        constructor(email: Email, tenantName: Tenant.UniqueName) : this("$email-$tenantName")
    }

    class Password(password: String?) : Value.AsString(password, StringUtils.PASSWORD_PATTERN)
}
