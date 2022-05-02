package ru.anyfon.common

import kotlin.Exception
import kotlin.reflect.KClass

abstract class Exception(message: String) : kotlin.Exception(message) {

    class AnonymousInstanceClass(supertype: KClass<*>) : Exception(
        "Class [ ${supertype.qualifiedName} ] cannot have anonymous instance"
    )

}
