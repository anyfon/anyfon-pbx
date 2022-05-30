package ru.anyfon.pbx.common.domain.service.message

class MessageParams(
    val defaultErrorMessage: String,
    val errorMessageSource: String? = null,
    val params: Map<String, String> = emptyMap()
)
