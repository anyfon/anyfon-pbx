package ru.anyfon.pbx.manager.domain.subscriber

import ru.anyfon.pbx.common.domain.Entity
import ru.anyfon.pbx.common.domain.EntityID
import ru.anyfon.pbx.common.domain.Value

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

    class Username(name: String?) : Value.AsString(name, "[a-z0-9\\-]{10,30}")

    class Password(password: String?) : Value.AsString(password, "\\S{10,30}")
}
