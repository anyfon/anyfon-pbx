package ru.anyfon.web.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import ru.anyfon.pbx.common.domain.Value


class NumberValueObjectSerializer : StdSerializer<Value.AsNumber<*>>(Value.AsNumber::class.java) {
    override fun serialize(value: Value.AsNumber<*>?, gen: JsonGenerator?, provider: SerializerProvider?) {
        if (value == null) {
            gen?.writeNull()
            return
        }
        gen?.writeNumber(value.value.toString())
    }
}
