package ru.anyfon.pbx.common.domain.service

interface MessageSource {
    fun getMessage(
        source: String?,
        defaultMessage: String?,
        params: Map<String, String> = emptyMap()
    ): String
}
