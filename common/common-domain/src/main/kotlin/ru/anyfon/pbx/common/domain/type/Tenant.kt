package ru.anyfon.pbx.common.domain.type

import ru.anyfon.pbx.common.domain.DomainEntity
import ru.anyfon.pbx.common.domain.EntityID
import ru.anyfon.pbx.common.domain.Value

interface Tenant : DomainEntity {
    val id: ID
    val uniqueName: UniqueName

    class ID (uuid: String?) : EntityID.AsUuidString(uuid) {
        companion object {
            val EMPTY = ID(EMPTY_UUID)
        }

        override fun isEmpty(): Boolean = this == EMPTY
    }

    class UniqueName(name: String) : Value.AsString(name, NAME_PATTERN) {
        companion object {
            const val NAME_PATTERN = "[a-z\\_\\-]{5,15}"
        }
    }
}
