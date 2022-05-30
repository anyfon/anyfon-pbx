package ru.anyfon.pbx.common.domain

import ru.anyfon.common.util.Asserts
import ru.anyfon.common.util.ConvertUtils
import java.util.*
import kotlin.reflect.KClass

interface Value<Value : Any> : java.io.Serializable, ValueObject {

    val value: Value

    abstract class Abstract<Value : Any>(
        value: Value?
    ) : ru.anyfon.pbx.common.domain.Value<Value> {
        final override val value: Value = value ?: throw NullPointerException("Must be not null")

        override fun toString(): String = value.toString()
    }

    interface AsStringType : Value<String>

    interface EnumAsString : AsStringType {
        override val value: String get() = this.toString()
    }

    abstract class AsString(
        value: Any?,
        pattern: String? = null
    ) : Abstract<String>(Asserts.requireStringMatching(value.toString(), pattern ?: ".*")), AsStringType {

        final override fun toString(): String = this.value

        final override fun equals(other: Any?): Boolean =
            other is AsString && other.value == value

        final override fun hashCode(): Int = Objects.hash(this.value)
    }

    abstract class AsUuidString(
        uuid: Any?
    ) : AsString(uuid, UUID_PATTERN) {
        companion object {
            const val UUID_PATTERN = "[a-zA-Z0-9\\-]{36}"
            const val EMPTY_UUID = "00000000-0000-0000-0000-000000000000"
        }
    }

    abstract class AsUUID(value: UUID) : Abstract<UUID>(value) {
        constructor(value: Any) : this(UUID.fromString(value.toString()))
    }

    interface AsNumber<T : Number> : Value<T>

    abstract class AsInt(value: Int?) : Abstract<Int>(value), AsNumber<Int> {
        constructor(value: Any?) : this(
            ConvertUtils.toNumber(value)?.toInt()
                ?: throw IllegalArgumentException("Value is not Int")
        )
    }

    abstract class AsLong(value: Long?) : Abstract<Long>(value), AsNumber<Long> {

        constructor(value: Any?) : this(
            ConvertUtils.toNumber(value)?.toLong()
                ?: throw IllegalArgumentException("Value is not Long")
        )
    }


    abstract class AsPositiveInt(
        value: Number?,
        includeZero: Boolean = false
    ) : AsInt(composeValue(value, includeZero)) {

        constructor(value: Any?) : this(
            ConvertUtils.toNumber(value)?.toInt()
                ?: throw IllegalArgumentException("Value is not Int")
        )

        private companion object {
            fun composeValue(value: Number?, includeZero: Boolean): Int {

                val intValue = value?.toInt() ?: -2

                if (intValue == -1) return intValue

                if (includeZero && intValue >= 0) {
                    return intValue
                } else if (!includeZero && intValue > 0) {
                    return intValue
                }

                throw IllegalArgumentException(
                    "Value must be positive or -1 (empty value)".let {
                        if (includeZero) {
                            return@let it.plus(" include 0")
                        }
                        return@let it
                    }
                )
            }
        }
    }

    class PositiveInt(
        value: Number?,
        includeZero: Boolean = false
    ) : AsPositiveInt(value, includeZero) {
        constructor(value: Any?, includeZero: Boolean = false) : this(
            ConvertUtils.toNumber(value)?.toInt()
                ?: throw IllegalArgumentException("Value is not Int"),
            includeZero
        )
    }

    class PositiveZeroInt(
        value: Number?
    ) : AsPositiveInt(value, true) {
        constructor(value: Any?) : this(
            ConvertUtils.toNumber(value)?.toInt()
                ?: throw IllegalArgumentException("Value is not Int")
        )
    }

    class PositiveNonZeroInt(
        value: Number?
    ) : AsPositiveInt(value, false) {
        constructor(value: Any?) : this(
            ConvertUtils.toNumber(value)?.toInt()
                ?: throw IllegalArgumentException("Value is not Int")
        )
    }

    abstract class AsPositiveLong(value: Number, includeZero: Boolean = false) : AsLong(composeValue(value, includeZero)) {

        constructor(value: Any?) : this(
            ConvertUtils.toNumber(value)?.toLong()
                ?: throw IllegalArgumentException("Value is not Long")
        )
        private companion object {
            fun composeValue(value: Number, includeZero: Boolean): Long {
                val longValue = value.toLong()
                if (includeZero && longValue >= 0) {
                    return longValue
                } else if (!includeZero && longValue > 0) {
                    return longValue
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
