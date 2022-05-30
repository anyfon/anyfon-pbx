package ru.anyfon.pbx.common.domain.service.request

import ru.anyfon.pbx.common.domain.service.message.MessageParams

class ValidatedField<Raw, Value>(
    fieldId: FieldID,
    row: Raw,
    errorMessages: MutableMap<FieldID, MessageParams>,
    fieldMapper: FieldMapper<Raw, Value>
) {

    constructor(
        fieldId: String,
        row: Raw,
        errorMessages: MutableMap<FieldID, MessageParams>,
        fieldMapper: FieldMapper<Raw, Value>
    ) : this(FieldID(fieldId), row, errorMessages, fieldMapper)

    val value: Value?

    init {
        value = try {
            val result = fieldMapper.map(row)
            try {
                result.valueSupplier.get()
            } catch (e: Exception) {
                throw ValidationException(result.errorMessage)
            }

        } catch (e: ValidationException) {
            errorMessages[fieldId] = e.messageParams
            null
        }
    }
}
