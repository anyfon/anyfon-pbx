package ru.anyfon.pbx.common.domain

import java.util.regex.Pattern

interface EntityID<Type : Any> : Value<Type> {

    abstract class AsString(
        id: String,
        pattern: String? = null
    ) : EntityID<String>, Value.AsString(id, pattern ?: DEFAULT_PATTERN) {

        companion object {
            const val DEFAULT_PATTERN = "[A-Z0-9\\-]{36}"
        }
    }

}
