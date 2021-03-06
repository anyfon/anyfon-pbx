package ru.anyfon.common.util


object StringUtils {

    const val ALLOWED_HTML_SYMBOLS_PATTERN = "[А-Яа-я\\w\\s\\;\\,\\.\\-\\+\\*\\%\\$\\#\\@\\!\\[\\]\\(\\)]*"

    const val PASSWORD_PATTERN = "[А-Яа-я\\w\\s\\;\\,\\.\\-\\+\\*\\%\\$\\#\\@\\!\\[\\]\\(\\)]{8,30}"

    const val ALL_LETTER_INT_DOG_PATTERN = "[А-Яа-я\\w\\s\\@]*"

    fun camelCaseToString(camelCase: String?, divider: String = " "): String? {
        val first = camelCase?.get(0)
        return first?.lowercase() + camelCase?.substring(1)?.flatMap {
            if (it.isUpperCase()) {
                val list = divider.toList().toMutableList()
                list.add(it.lowercaseChar())
                return@flatMap list
            }
            return@flatMap listOf(it)
        }?.joinToString(separator = "")?.trim()
    }

    fun stringToCamelCase(string: String?, divider: String = " ", allLowerCase: Boolean = true): String? {
        val split = string?.split(divider)
        return split?.map {
            val item = if (allLowerCase) it.lowercase() else it
            item.firstToUpper()
        }?.joinToString("")?.trim()
    }
}

fun CharSequence?.firstToUpper(): String? {
    return this?.first()?.uppercase()?.plus(this.substring(1))
}

fun CharSequence?.firstToLower(): String? {
    return this?.first()?.lowercase()?.plus(this.substring(1))
}
