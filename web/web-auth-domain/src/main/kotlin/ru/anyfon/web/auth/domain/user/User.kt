package ru.anyfon.web.auth.domain.user

import ru.anyfon.pbx.common.domain.DomainEntity
import ru.anyfon.pbx.common.domain.EntityID
import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.NamePart
import ru.anyfon.pbx.common.domain.type.PhoneNumber

class User(
    val id: ID,
    val firstName: NamePart,
    val middleName: NamePart?,
    val lastName: NamePart,
    val role: UserRole,
    val email: Email,
    val phoneNumber: PhoneNumber.External,
    val password: String,
    val enabled: Boolean
) : DomainEntity {
    class ID(uuid: String) : EntityID.AsUuidString(uuid) {
        companion object {
            val EMPTY = ID(EMPTY_UUID)
        }
        override fun isEmpty(): Boolean = this == EMPTY
    }

    fun hidePassword() : User = User(
        id, firstName, middleName, lastName, role, email, phoneNumber, "", enabled
    )
}
