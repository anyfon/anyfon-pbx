package ru.anyfon.pbx.common.domain.type

import ru.anyfon.pbx.common.domain.EntityID

class TenantID (uuid: String) : EntityID.AsUuidString(uuid) {
    companion object {
        val EMPTY = TenantID("00000000-0000-0000-0000-000000000000")
    }
}
