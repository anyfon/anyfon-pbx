package ru.anyfon.pbx.common.domain

interface EntityID<Type : Any> : Value<Type> {

    abstract class AsString(
        id: String,
        pattern: String? = null
    ) : EntityID<String>, Value.AsString(id, pattern)

    abstract class AsUuidString(
        uuid: String
    ) : AsString(uuid, UUID_PATTERN) {
        companion object {
            const val UUID_PATTERN = "[a-zA-Z0-9\\-]{36}"
        }
    }

}
