package ru.anyfon.pbx.common.domain.service

interface ValidatedRequest {

    val isValid: Boolean get() = !violations.iterator().hasNext()

    val violations: Iterable<Violation>

    companion object {
        val WRONG = object : ValidatedRequest {
            override val violations: Iterable<Violation> = emptyList()
        }

        val VALID = object : ValidatedRequest {
            override val violations: Iterable<Violation> = emptyList()
            override val isValid: Boolean = true
        }

        fun create(errorMap: Map<FieldID, String>): ValidatedRequest =
            object : ValidatedRequest {
                override val violations: Iterable<Violation> = errorMap.map {
                    Violation.of(it.key, it.value)
                }
            }

        fun compose(
            messagesMap: Map<FieldID, MessageParams>,
            messageSource: MessageSource?
        ): ValidatedRequest = object : ValidatedRequest {
            override val violations: Iterable<Violation> = messagesMap.map {
                var message = it.value.defaultErrorMessage
                if (messageSource != null && it.value.errorMessageSource != null) {
                    message = messageSource.getMessage(
                        it.value.errorMessageSource,
                        it.value.defaultErrorMessage,
                        it.value.params
                    )
                }
                Violation.of(it.key, message)

            }

        }
    }

}
