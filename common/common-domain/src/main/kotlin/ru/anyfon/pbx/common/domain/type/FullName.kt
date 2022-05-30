package ru.anyfon.pbx.common.domain.type

import ru.anyfon.pbx.common.domain.Value

class FullName(name: String?) : Value.AsString(name, PATTERN) {

    constructor(firstName: NamePart, middleName: NamePart?, lastName: NamePart) : this(
        middleName?.let {
            "$firstName $middleName $lastName"
        } ?: "$firstName $lastName"
    )
    companion object {
        const val PATTERN = "[а-яА-Я\\w\\s]{5,120}"
    }
}
