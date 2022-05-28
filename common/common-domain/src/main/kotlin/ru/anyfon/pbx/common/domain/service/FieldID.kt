package ru.anyfon.pbx.common.domain.service

import ru.anyfon.common.util.Asserts
import ru.anyfon.pbx.common.domain.Value
import java.util.*

class FieldID(id: String, val index: Int? = null) : Value.AsStringType {

    override val value: String

    init {
        value = Asserts.requireStringMatching(id, "[a-zA-Z\\.]{2,50}")
    }

    fun withIndex(index: Int) : FieldID = FieldID(value, index)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FieldID) return false

        if (index != other.index) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int = Objects.hash(value, index)

    override fun toString(): String = index?.let {
        value.plus("[$it]")
    } ?: value

}
