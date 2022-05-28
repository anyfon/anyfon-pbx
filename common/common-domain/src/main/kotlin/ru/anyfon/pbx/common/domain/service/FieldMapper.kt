package ru.anyfon.pbx.common.domain.service

import java.util.function.Supplier

fun interface FieldMapper<Raw, Value> {
    fun map(raw: Raw): Result<Value>

    class Result<Value>(
        val errorMessage: MessageParams,
        val valueSupplier: Supplier<Value>,
    ) {
        constructor(
            defaultErrorMessage: String,
            errorMessageSource: String?,
            params: Map<String, String>,
            valueSupplier: Supplier<Value>
        ) : this(MessageParams(defaultErrorMessage, errorMessageSource, params), valueSupplier)

        constructor(
            defaultErrorMessage: String,
            valueSupplier: Supplier<Value>
        ) : this(defaultErrorMessage, null, emptyMap(), valueSupplier)
    }
}
