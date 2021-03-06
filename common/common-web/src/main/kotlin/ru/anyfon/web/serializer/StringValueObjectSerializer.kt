package org.ecom24.platform.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import ru.anyfon.pbx.common.domain.Value

class StringValueObjectSerializer : StdSerializer<Value.AsStringType>(Value.AsStringType::class.java) {


    override fun serialize(value: Value.AsStringType?, gen: JsonGenerator?, provider: SerializerProvider?) {
        if (value == null) {
            gen?.writeNull()
            return
        }
        gen?.writeString(value.value)
    }
}
