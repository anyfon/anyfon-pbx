package ru.anyfon.web.auth.domain.user.service


import ru.anyfon.pbx.common.domain.Entity
import ru.anyfon.pbx.common.domain.type.*

class UserData(
    override val uuid: User.Uuid,
    val firstName: NamePart,
    val middleName: NamePart?,
    val lastName: NamePart,
    override val role: User.Role,
    override val email: Email,
    override val phoneNumber: PhoneNumber.External,
    val enabled: Boolean
) : User, Entity {

    override val fullName: FullName = FullName(firstName, middleName, lastName)

}
