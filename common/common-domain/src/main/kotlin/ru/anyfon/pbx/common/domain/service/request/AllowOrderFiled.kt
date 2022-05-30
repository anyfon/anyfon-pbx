package ru.anyfon.pbx.common.domain.service.request

import ru.anyfon.common.util.ConvertUtils

class AllowOrderFiled(
    private val validFields: Set<FieldID>
) {
    constructor(vararg fieldId: FieldID) : this(fieldId.toSet())

    fun parse(rawFields: Iterable<String>): Iterable<FieldID> = rawFields.mapNotNull {
        ConvertUtils.tryOrNull { FieldID(it) }.let {
            if (!validFields.contains(it)) return@let null
            it
        }
    }

}
