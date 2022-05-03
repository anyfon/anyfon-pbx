package ru.anyfon.pbx.common.domain.type

import ru.anyfon.pbx.common.domain.Value

class NamePart(name: String) : Value.AsString(name, PATTERN) {
    companion object {
        const val PATTERN = "[А-Яа-я\\w\\s]{2,25}"
    }
}
