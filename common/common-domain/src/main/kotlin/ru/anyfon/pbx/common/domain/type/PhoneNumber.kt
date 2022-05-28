package ru.anyfon.pbx.common.domain.type

import ru.anyfon.common.Exception
import ru.anyfon.common.util.Asserts
import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.pbx.common.domain.Value
import java.util.*

interface PhoneNumber<Type : PhoneNumber.Type> : Value<String> {
    companion object {
        const val EXTENSION_PREFIX = "000"
    }

    val type: Type

    val name: FullName?

    val number: String

    abstract class Abstract<Type : PhoneNumber.Type>(
        number: kotlin.Any?,
        name: String?,
        final override val type: Type
    ) : PhoneNumber<Type>, Value.AsStringType {

        final override val name: FullName? = ConvertUtils.tryOrNull { FullName(name) }

        final override val number: String = requiredNumber(number, type)

        final override val value: String = name?.let {
            "${this.number} <${this.name}>"
        } ?: this.number

        protected companion object {
            fun cleanedNumber(number: kotlin.Any?): String? = number?.toString()?.let {
                if (it == "anonymous" || it == "Unavailable") return it
                return it.replace("\\D".toRegex(), "")
            }

            protected fun requiredNumber(number: kotlin.Any?, requiredType: Type): String = cleanedNumber(number).let {
                Asserts.requireStringMatching(it, requiredType.pattern)
            }

        }

        final override fun toString(): String = this.value

        final override fun equals(other: kotlin.Any?): Boolean =
            other is PhoneNumber<*> && other.number == number

        final override fun hashCode(): Int = Objects.hash(this.number)

    }

    class Internal(
        number: kotlin.Any?,
        name: String? = null
    ) : Abstract<Type.Internal>(number, name, Type.Internal)

    class Extension(
        number: kotlin.Any?,
        name: String? = null
    ) : Abstract<Type.Extension>(number, name, Type.Extension) {

        val prefix: String = EXTENSION_PREFIX

        val tenantPrefix: String = this.number.substring(3, 9)

        val internalNumber: Internal = Internal(this.number.substring(9), name)

    }

    class External(
        number: kotlin.Any?,
        name: String? = null
    ) : Abstract<Type.External>(number, name, Type.External)

    class Any(number: kotlin.Any?, name: String? = null) : Abstract<Type>(number, name, determineType(number)) {
        private companion object {
            fun determineType(number: kotlin.Any?): Type {

                val strNumber = cleanedNumber(number)

                if (Type.Internal.pattern.toRegex().matches(strNumber.toString())) return Type.Internal
                if (Type.Extension.pattern.toRegex().matches(strNumber.toString())) return Type.Extension
                if (Type.External.pattern.toRegex().matches(strNumber.toString())) return Type.External

                throw IllegalArgumentException("Arg number [ $number ] is not phone number")
            }
        }

        val isExtension: Boolean = type == Type.Extension

        val isExternal: Boolean = type == Type.External

        val isInternal: Boolean = type == Type.Internal

    }

    interface Type : Value<String> {

        val pattern: String

        override val value: String
            get() =
                this::class.simpleName
                    ?: throw Exception.AnonymousInstanceClass(Type::class)

        object Internal : Type {
            override val pattern: String = "[1-9]\\d{4,6}"
        }

        object Extension : Type {
            override val pattern: String = "$EXTENSION_PREFIX\\d{9,12}"
        }

        object External : Type {
            override val pattern: String = "(^(?!000)\\d{7,16}|anonymous|Unavailable)"

        }
    }

    fun PhoneNumber<PhoneNumber.Type.External>.cloneExternal(): External =
        External(this@PhoneNumber.number, this@PhoneNumber.name.toString())

    fun PhoneNumber<PhoneNumber.Type.Extension>.cloneExtension(): Extension =
        Extension(this@PhoneNumber.number, this@PhoneNumber.name.toString())

    fun PhoneNumber<PhoneNumber.Type.Internal>.cloneInternal(): Internal =
        Internal(this@PhoneNumber.number, this@PhoneNumber.name.toString())
}
