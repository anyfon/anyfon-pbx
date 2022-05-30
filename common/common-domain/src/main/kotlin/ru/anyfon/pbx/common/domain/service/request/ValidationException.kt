package ru.anyfon.pbx.common.domain.service.request

import ru.anyfon.pbx.common.domain.service.message.MessageParams

class ValidationException(
    val messageParams: MessageParams
) : Exception(messageParams.defaultErrorMessage)
