package ru.anyfon.pbx.common.domain

import ru.anyfon.pbx.common.domain.service.request.FieldID
import ru.anyfon.pbx.common.domain.service.request.Permission
import ru.anyfon.pbx.common.domain.service.request.ValidatedRequest
import kotlin.reflect.KClass

abstract class DomainException(message: String, requestId: RequestId) : Exception(message) {

    open val response: Response.Failed<*> = Response.Failed<Nothing>(message, requestId)

    class EntityNotFound(
        entityClass: KClass<out Entity>,
        id: EntityID<*>,
        requestId: RequestId = RequestId.EMPTY
    ) : DomainException(composeMessage(entityClass.qualifiedName, id), requestId) {

        private companion object {
            fun composeMessage(entityName: String?, id: EntityID<*>): String =
                "Entity [ name: $entityName, id: $id ] not found"
        }

        override val response: Response.Failed<*> =
            Response.Failed<Nothing>(composeMessage(entityClass.simpleName, id), requestId)
    }

    class BadRequestParam(
        paramName: String,
        value: Any?,
        requestId: RequestId = RequestId.EMPTY
    ) : DomainException("Bad request [ param: $paramName, value: ${value.toString()} ] ", requestId)

    class RequestValidateException(
        validatedRequest: ValidatedRequest,
        requestId: RequestId = RequestId.EMPTY
    ) : DomainException("Validation error", requestId) {

        val errors: Map<FieldID, String> = validatedRequest.violations.associateBy(
            { it.fieldId },
            { it.message }
        )

        override val response: Response.Failed<Map<FieldID, String>> =
            Response.Failed(message!!, requestId, errors)
    }

    class DomainPermission(
        domainClass: KClass<out DomainEntity>,
        permission: Permission,
        requestId: RequestId = RequestId.EMPTY
    ) : DomainException(composeMessage(domainClass.qualifiedName, permission), requestId) {

        private companion object {
            fun composeMessage(domainName: String?, permission: Permission): String =
                "Permission denied [ domain: $domainName, permit: $permission ".let {
                    if (permission is Permission.EntityIdAware<*>) it.plus("id: ${permission.entityID} ")
                    it.plus("]")
                }
        }

        override val response: Response.Failed<*> =
            Response.Failed<Nothing>(composeMessage(domainClass.simpleName, permission), requestId)
    }

    class DomainPermissions(
        domainClass: KClass<out DomainEntity>,
        deniedPermissions: List<Permission.EntityIdAware<*>>,
        requestId: RequestId = RequestId.EMPTY
    ) : DomainException(composeMessage(domainClass.qualifiedName, deniedPermissions), requestId) {

        private companion object {
            fun composeMessage(domainName: String?, deniedPermissions: Iterable<Permission.EntityIdAware<*>>): String =
                "Permissions denied [ domain: $domainName, items: ".let {
                    deniedPermissions.map { item ->
                        return@map "{ ${item.entityID}: ${item.type} }"
                    }.joinToString(",")

                    it.plus(" ]")
                }
        }

        override val response: Response.Failed<*> =
            Response.Failed(
                composeMessage(domainClass.simpleName, deniedPermissions),
                requestId,
                deniedPermissions.associateBy({ it.entityID },{ it.type } )
            )
    }
}
