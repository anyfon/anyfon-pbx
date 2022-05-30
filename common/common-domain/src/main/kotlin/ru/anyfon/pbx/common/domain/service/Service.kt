package ru.anyfon.pbx.common.domain.service

import ru.anyfon.common.util.ConvertUtils
import ru.anyfon.pbx.common.domain.DomainEntity
import ru.anyfon.pbx.common.domain.DomainException
import ru.anyfon.pbx.common.domain.RequestId
import ru.anyfon.pbx.common.domain.service.message.MessageParams
import ru.anyfon.pbx.common.domain.service.request.*
import java.util.function.Supplier
import kotlin.reflect.KClass

interface Service {

    fun <Raw, Val> Service.validIterableParam(
        paramName: String,
        rawValue: Iterable<Raw>,
        context: RequestContext,
        mapper: FieldMapper<Raw, Val>
    ): Request.Valid<List<Val>> {

        val errorMessages: MutableMap<FieldID, MessageParams> = mutableMapOf()

        val handler = IterableFieldHandler(paramName, rawValue, errorMessages, mapper)

        if (!handler.hasErrors) return Request.Valid(handler.value)

        throw DomainException.RequestValidateException(
            ValidatedRequest.compose(errorMessages, context.messageSource()),
            context.requestId
        )
    }

    fun <T> Service.validParam(paramName: String, rawValue: Any?, requestId: RequestId, supplier: Supplier<T>): Request.Valid<T> =
        ConvertUtils.tryOrNull {
            Request.Valid(supplier.get())
        } ?: throw DomainException.BadRequestParam(paramName, rawValue, requestId)

    fun <Req : Request> Service.validRequest(request: Req, context: RequestContext): Request.Valid<Req> =
        request.validate(context.messageSource()).let {
            if (it.isValid) return@let Request.Valid(request)
            throw DomainException.RequestValidateException(it, context.requestId)
        }

    fun Service.checkPermission(
        context: RequestContext,
        domainClass: KClass<out DomainEntity>,
        permission: Permission,
    ) {
        context.domainPermission(domainClass).hasPermit(permission).let {
            if (it) return
            throw DomainException.DomainPermission(domainClass, permission, context.requestId)
        }
    }

    fun Service.checkPermissions(
        context: RequestContext,
        domainClass: KClass<out DomainEntity>,
        permissions: Iterable<Permission.EntityIdAware<*>>
    ) {
        permissions.filterNot {
            context.domainPermission(domainClass).hasPermit(it)
        }.let {
            if (it.isEmpty()) return
            throw DomainException.DomainPermissions(domainClass, it, context.requestId)
        }
    }
}
