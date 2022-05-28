package ru.anyfon.pbx.common.domain.type

import ru.anyfon.pbx.common.domain.Value

class FullName(name: String?) : Value.AsString(name, PATTERN) {
    companion object {
        const val PATTERN = "[а-яА-Я\\w\\s]{5,120}"
    }
}
