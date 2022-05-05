package ru.anyfon.web.auth.domain.user

import ru.anyfon.pbx.common.domain.DomainEntity
import ru.anyfon.pbx.common.domain.EntityID
import ru.anyfon.pbx.common.domain.type.Email
import ru.anyfon.pbx.common.domain.type.PhoneNumber
import ru.anyfon.pbx.common.domain.type.NamePart

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
    class ID(uuid: String) : EntityID.AsUuidString(uuid)

    fun hidePassword() : User = User(
        id, firstName, middleName, lastName, role, email, phoneNumber, "", enabled
    )
}
