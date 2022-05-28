package ru.anyfon.pbx.common.domain.service

interface Request {
    fun validate(messageSource: MessageSource? = null) : ValidatedRequest
}
