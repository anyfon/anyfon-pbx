package ru.anyfon.pbx.common.domain.type

import ru.anyfon.pbx.common.domain.Value

class TenantId(id: String) : Value.AsString(id, PATTERN) {

    companion object {
        const val PATTERN = "[a-zA-Z\\_\\-]{5,20}"

        val EMPTY = TenantId("empty-tenant")

        fun orNull(id: String?) : TenantId? = id?.let { TenantId(id) }
    }
}
