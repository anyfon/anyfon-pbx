package ru.anyfon.pbx.common.domain.service.request

import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.pbx.common.domain.Value
import ru.anyfon.pbx.common.domain.service.message.MessageSource

interface Request {

    fun validate(messageSource: MessageSource? = null) : ValidatedRequest

    abstract class List(
        offset: String?,
        limit: String?
    ) : Request {

        val offset: Value.PositiveZeroInt = ConvertUtils.tryOrNull {
            Value.PositiveZeroInt(offset)
        } ?: Value.PositiveZeroInt(0)

        val limit: Value.PositiveZeroInt = ConvertUtils.tryOrNull {
            Value.PositiveZeroInt(limit)
        } ?: Value.PositiveZeroInt(100)

        abstract val orderBy: Iterable<FieldID>
    }

    class Valid<out Req>(val value: Req)
}
