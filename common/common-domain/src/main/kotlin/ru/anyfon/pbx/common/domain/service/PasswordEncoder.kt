package ru.anyfon.pbx.common.domain.service

fun interface PasswordEncoder {
    fun encode(passwordRaw: String): String
}
