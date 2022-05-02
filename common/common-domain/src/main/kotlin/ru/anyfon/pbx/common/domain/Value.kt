package ru.anyfon.pbx.common.domain

import ru.anyfon.common.util.Asserts
import ru.anyfon.common.util.ConvertUtils
import java.util.*
import kotlin.reflect.KClass

interface Value<Value : Any> : java.io.Serializable, ValueObject {

    val value: Value

    abstract class Abstract<Value : Any>(final override val value: Value) :
        ru.anyfon.pbx.common.domain.Value<Value>

    interface AsStringType : Value<String>

    interface EnumAsString : AsStringType {
        override val value: String get() = this.toString()
    }

    abstract class AsString(
        value: String?,
        pattern: String? = null
    ) : Abstract<String>(Asserts.requireStringMatching(value, pattern ?: ".*")), AsStringType {

        final override fun toString(): String = this.value

        final override fun equals(other: Any?): Boolean =
            other is AsString && other.value == value

        final override fun hashCode(): Int = Objects.hash(this.value)
    }

    abstract class AsUUID(value: UUID) : Abstract<UUID>(value) {
        constructor(value: Any) : this(UUID.fromString(value.toString()))
    }

    interface AsNumber<T : Number> : Value<T>

    abstract class AsInt(value: Int) : Abstract<Int>(value), AsNumber<Int> {
        constructor(value: Any) : this(
            ConvertUtils.toNumber(value)?.toInt() ?: throw IllegalArgumentException("Value is null")
        )
    }

    abstract class AsLong(value: Long) : Abstract<Long>(value), AsNumber<Long> {
        constructor(value: Any) : this(
            ConvertUtils.toNumber(value)?.toLong() ?: throw IllegalArgumentException("Value is null")
        )
    }

    abstract class AsPositiveInt(value: Int, includeZero: Boolean = false) : AsInt(composeValue(value, includeZero)) {

        private companion object {
            fun composeValue(value: Int, includeZero: Boolean): Int {
                if (includeZero && value >= 0) {
                    return value
                } else if (!includeZero && value > 0) {
                    return value
                }
                throw IllegalArgumentException(
                    "Value must be positive".let {
                        if (includeZero) {
                            return@let it.plus(" include 0")
                        }
                        return@let it
                    }
                )
            }
        }
    }

    abstract class AsSingleton<Self : AsSingleton<Self>>(singletonClass: KClass<out Self>) : Value<String> {


        final override fun toString(): String = value

        final override fun equals(other: Any?): Boolean =
            other is AsSingleton<*> && this.hashCode() == other.hashCode()

        final override fun hashCode(): Int =
            Objects.hashCode(this::class)
    }
}
