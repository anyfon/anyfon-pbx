package ru.anyfon.pbx.common.domain.type

import ru.anyfon.pbx.common.domain.Value

interface PhoneNumber : Value<String> {

    val type: Type

    abstract class Abstract(
        number: kotlin.Any?,
        override val type: Type = Type.ANY
    ) : PhoneNumber, Value.AsString(
        requiredNumber(number),
        type.pattern
    ) {
        private companion object {
            fun requiredNumber(number: kotlin.Any?) : String? {
                val stringNumber = number?.toString() ?: return null
                if (stringNumber == "anonymous" || stringNumber == "Unavailable") return stringNumber
                return stringNumber.replace("\\D".toRegex(),"")
            }
        }
    }

    class Internal(number: kotlin.Any?) : Abstract(number, Type.INTERNAL)

    class External(number: kotlin.Any?) : Abstract(number, Type.EXTERNAL)

    class Any(number: kotlin.Any?) : Abstract(number) {
        val isInternal: Boolean = type == Type.INTERNAL
        val isExternal: Boolean = type == Type.EXTERNAL
    }


    enum class Type : Value<String> {

        INTERNAL {
            override val pattern = "(\\d{3,6}|anonymous|Unavailable)"
        },
        EXTERNAL {
            override val pattern = "(\\d{7,16}|anonymous|Unavailable)"
        },
        ANY {
            override val pattern = "(\\d{3,16}|anonymous|Unavailable)"
        };

        override val value: String = this.toString()

        abstract val pattern: String
    }

}
