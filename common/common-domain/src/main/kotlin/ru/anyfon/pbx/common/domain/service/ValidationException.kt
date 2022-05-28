package ru.anyfon.pbx.common.domain.service

class ValidationException(
    val messageParams: MessageParams
) : Exception(messageParams.defaultErrorMessage)
