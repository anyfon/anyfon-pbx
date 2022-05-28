package ru.anyfon.pbx.common.domain

import ru.anyfon.common.util.ConvertUtils

interface EntityID<Type : Any> : Value<Type> {

    fun isEmpty() : Boolean
    abstract class AsLong(
        id: Number
    ) : EntityID<Long>, Value.AsPositiveLong(id, true) {
        constructor(id: Any?) : this(
            ConvertUtils.toNumber(id)
                ?: throw IllegalArgumentException("Impossible to parse [ id: {$id} ] to number")
        )
    }

    abstract class AsString(
        id: String?,
        pattern: String? = null
    ) : EntityID<String>, Value.AsString(id, pattern)

    abstract class AsUuidString(
        uuid: String?
    ) : AsString(uuid, UUID_PATTERN) {
        companion object {
            const val UUID_PATTERN = "[a-zA-Z0-9\\-]{36}"
            const val EMPTY_UUID = "00000000-0000-0000-0000-000000000000"
        }
    }

}
