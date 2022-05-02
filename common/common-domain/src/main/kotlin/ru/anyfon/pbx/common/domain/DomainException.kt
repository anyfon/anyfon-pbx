package ru.anyfon.pbx.common.domain

import kotlin.reflect.KClass

abstract class DomainException(message: String) : ru.anyfon.common.Exception(message) {
    class IllegalFieldValue(domainType: KClass<*>, fieldName: String, value: Any? = null) : DomainException(
        "Field [ $fieldName ] has a forbidden value [ $value ], domain class [ ${domainType.qualifiedName } ]"
    )
}
