package ru.anyfon.common.util

import kotlin.reflect.KClass

object ClassUtils {
    fun nameOfClass(type: KClass<out Any>, divider: String = " ") : String? {
        return StringUtils.camelCaseToString(type.simpleName, divider)
    }
}
