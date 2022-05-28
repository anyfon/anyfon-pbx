package ru.anyfon.pbx.common.domain.service

interface Violation {

    val fieldId: FieldID

    val message: String

    companion object {

        fun of(
            fieldID: FieldID,
            message: String
        ) : Violation = object : Violation {
            override val fieldId: FieldID = fieldID
            override val message: String = message
        }

    }

}
