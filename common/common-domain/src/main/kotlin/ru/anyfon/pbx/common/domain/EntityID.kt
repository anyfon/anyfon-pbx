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
        id: Any?,
        pattern: String? = null
    ) : EntityID<String>, Value.AsString(id, pattern)

    abstract class AsUuidString(
        uuid: Any?
    ) : Value.AsUuidString(uuid), EntityID<String>

}
