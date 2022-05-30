package ru.anyfon.common.util

import kotlin.reflect.KClass
import kotlin.reflect.full.staticFunctions

object ConvertUtils {
    fun toNumber(source: Any?): Number? {
        return when (source) {
            is Number -> source
            is String -> source.replace("\\D".toRegex(), "").toBigDecimalOrNull()
            else -> null
        }
    }

    fun toMap(source: Any?): Map<*, *>? {
        return when (source) {
            is Map<*, *> -> source
            else -> null
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Enum<*>> toEnum(source: Any?, type: KClass<T>): T? {
        return tryOrNull {
            val a = type.staticFunctions.first { it.name == "valueOf" }
            a.call(source) as T
        }
    }

    fun <T> tryOrNull(init: () -> T): T? {
        return try {
            init()
        } catch (_: Exception) {
            null
        }
    }
}

fun Any?.toNumber(): Number? = ConvertUtils.toNumber(this)

fun Any?.toMap(): Map<*, *>? = ConvertUtils.toMap(this)
