package ru.anyfon.pbx.common.domain.service

class IterableFieldHandler<Raw, Value>(
    fieldId: FieldID,
    iterableRaw: Iterable<Raw>,
    errorMessages: MutableMap<FieldID, MessageParams>,
    itemMapper: FieldMapper<Raw, Value>
) {

    constructor(
        fieldId: String,
        iterableRaw: Iterable<Raw>,
        errorMessages: MutableMap<FieldID, MessageParams>,
        itemMapper: FieldMapper<Raw, Value>
    ) : this(FieldID(fieldId), iterableRaw, errorMessages, itemMapper)

    val value: List<Value>

    init {
        value = iterableRaw.let {
            var i = 0
            it.toList().mapNotNull { item ->
                try {
                    val result = itemMapper.map(item)
                    try {
                        result.valueSupplier.get().also {
                            i++
                        }
                    } catch (e: Exception) {
                        throw ValidationException(result.errorMessage)
                    }

                } catch (e: ValidationException) {
                    errorMessages[fieldId.withIndex(i)] = e.messageParams
                    null.also {
                        i++
                    }
                }
            }
        }
    }
}
