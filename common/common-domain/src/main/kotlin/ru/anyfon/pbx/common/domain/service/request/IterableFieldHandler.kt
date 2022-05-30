package ru.anyfon.pbx.common.domain.service.request

import ru.anyfon.pbx.common.domain.service.message.MessageParams

class IterableFieldHandler<Raw, Value>(
    fieldId: FieldID,
    iterableRaw: Iterable<Raw>,
    private val errorMessages: MutableMap<FieldID, MessageParams>,
    itemMapper: FieldMapper<Raw, Value>
) {

    constructor(
        fieldId: String,
        iterableRaw: Iterable<Raw>,
        errorMessages: MutableMap<FieldID, MessageParams>,
        itemMapper: FieldMapper<Raw, Value>
    ) : this(FieldID(fieldId), iterableRaw, errorMessages, itemMapper)

    val value: List<Value>

    val hasErrors get() = errorMessages.isNotEmpty()

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
