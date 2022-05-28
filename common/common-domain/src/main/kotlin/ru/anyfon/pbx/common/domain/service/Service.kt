package ru.anyfon.pbx.common.domain.service

import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.pbx.common.domain.DomainException
import java.util.function.Supplier

interface Service {
    fun <T> Service.validParam(paramName: String, rawValue: Any?, supplier: Supplier<T>): T =
        ConvertUtils.tryOrNull {
            supplier.get()
        } ?: throw DomainException.BadRequestParam(paramName, rawValue)

    fun <Req : Request> Service.validRequest(request: Req, messageSource: MessageSource? = null) : Req =
        request.validate(messageSource).let {
            if (it.isValid) return@let request
            throw DomainException.RequestValidateException(it)
        }
}
