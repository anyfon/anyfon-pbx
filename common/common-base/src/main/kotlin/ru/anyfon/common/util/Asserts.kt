package ru.anyfon.common.util

object Asserts {

    fun <T> requireNotNull(value: T?, message: String? = null): T {
        if (value == null) {
            throw NullPointerException(message?:"Arg must not be null")
        }
        return value
    }

    fun requireStringMatching(value: String?, regex: String, error: String? = null): String {
        val exception = {
            val message = "String [ $value ] contains illegal symbols. Required pattern: [ $regex ]"
            IllegalArgumentException(error ?: message)
        }
        return requireStringMatching(value, regex, exception)
    }

    fun requireStringMatching(value: String?, regex: String, exception: () -> Exception) :String {
        if (value == null || !regex.toRegex().matches(value)) throw exception()
        return value
    }
}
