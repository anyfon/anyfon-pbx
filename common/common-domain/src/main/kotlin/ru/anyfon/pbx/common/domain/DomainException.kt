package ru.anyfon.pbx.common.domain

import ru.anyfon.pbx.common.domain.service.FieldID
import ru.anyfon.pbx.common.domain.service.ValidatedRequest
import kotlin.reflect.KClass

abstract class DomainException(message: String) : ru.anyfon.common.Exception(message) {
    class IllegalFieldValue(domainType: KClass<*>, fieldName: String, value: Any? = null) : DomainException(
        "Field [ $fieldName ] has a forbidden value [ $value ], domain class [ ${domainType.qualifiedName} ]"
    )

    class EntityNotFound(entityClass: KClass<out Entity>, id: String) : DomainException(
        "Entity [ class: ${entityClass.qualifiedName}, id: $id ] not found"
    )

    class BadRequestParam(paramName: String, value: Any?) : DomainException(
        "Bad request [ param: $paramName, value: ${value.toString()}] "
    )

    class RequestValidateException(validatedRequest: ValidatedRequest) : DomainException(
        "Validation error"
    ) {
        val errors: Map<FieldID, String> = validatedRequest.violations.associateBy(
            { it.fieldId },
            { it.message }
        )
    }
}
