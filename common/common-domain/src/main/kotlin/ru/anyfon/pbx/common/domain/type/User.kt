package ru.anyfon.pbx.common.domain.type

import ru.anyfon.common.util.StringUtils
import ru.anyfon.pbx.common.domain.EntityID
import ru.anyfon.pbx.common.domain.Value
import ru.anyfon.pbx.common.domain.ValueObject

interface User {

    val uuid: Uuid

    val fullName: FullName

    val phoneNumber: PhoneNumber.External

    val email: Email

    val role: Role

    class Uuid(uuid: String) : EntityID.AsUuidString(uuid) {
        companion object {
            val Empty = Uuid(EMPTY_UUID)
        }

        override fun isEmpty(): Boolean = this == Empty
    }

    class Role(name: String?) : Value.AsString(name, "[A-Z\\_]{3,10}"), ValueObject {

        companion object {
            val Admin = Role("ADMIN")

            val Root = Role("Root")

            val Customer = Role("CUSTOMER")
        }
    }

    class Password(password: String?) : Value.AsString(password, StringUtils.PASSWORD_PATTERN)
}
